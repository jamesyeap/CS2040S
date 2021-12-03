import java.util.*;
import java.io.*;
import java.util.Objects; // needed to generate a hashcode

public class Solution {
	static Kattio io;

	static int R;
	static int C;
	

	public static void main(String[] args) {
		io = new Kattio(System.in, System.out);

		R = io.getInt();
		C = io.getInt();

		int[][] vertices = new int[R*C][2];
		int nVertices = 0;

		int[][] land = new int[R][C];
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				land[i][j] = io.getInt();

				vertices[nVertices] = new int[]{i,j};
				nVertices++;
			}
		}

		int[][] D = new int[R*C][R*C];		

		for (int i=0; i<D.length; i++) {
			Arrays.fill(D[i], Integer.MAX_VALUE);
		}

		for (int i=0; i<vertices.length; i++) {
			int[] currV = vertices[i];

			for (int j=0; j<vertices.length; j++) {								
				int[] adjV = vertices[j];

				if (!(currV[0] == adjV[0] && currV[1] == adjV[1])) {
					if (Math.abs(currV[0]-adjV[0]) == 1 && Math.abs(currV[0]-adjV[1]) == 0) {
						D[i][j] = Math.max(land[currV[0]][currV[1]], land[adjV[0]][adjV[1]]);
						D[j][i] = Math.max(land[currV[0]][currV[1]], land[adjV[0]][adjV[1]]);
					}

					if (Math.abs(currV[0]-adjV[0]) == 0 && Math.abs(currV[0]-adjV[1]) == 1) {
						D[i][j] = Math.max(land[currV[0]][currV[1]], land[adjV[0]][adjV[1]]);
						D[j][i] = Math.max(land[currV[0]][currV[1]], land[adjV[0]][adjV[1]]);
					}
				} else {
					D[i][j] = 0;
				}
			}
		}

		for (int i=0; i<D.length; i++) {
			io.println(Arrays.toString(D[i]));
		}		

		for (int k=0; k<vertices.length; k++) {
			int[] middleV = vertices[k];

			for (int i=0; i<vertices.length; i++) {
				int[] sourceV = vertices[i];

				for (int j=0; j<vertices.length; j++) {
					int[] destinationV = vertices[j];

					if ( areAdjacent(sourceV, middleV) && areAdjacent(middleV, destinationV) ) {
						D[i][j] = Math.min(D[i][j], Math.max(D[i][k], D[k][j]));
					}
				}
			}
		}
		
		for (int i=0; i<D.length; i++) {
			io.println(Arrays.toString(D[i]));
		}

		io.close();
	}

	static boolean areAdjacent(int[] v1, int[] v2) {
		return (Math.abs(v1[0]-v2[0]) == 1 && Math.abs(v1[0]-v2[1]) == 0) || (Math.abs(v1[0]-v2[0]) == 0 && Math.abs(v1[0]-v2[1]) == 1);
	}
}

/* OLD STUFF 
public class Solution {
	static Kattio io;

	static int R;
	static int C;
	static int best = Integer.MAX_VALUE;

	static HashMap<Grid, PriorityQueue<Edge>> adjList;
	static boolean reached;
	static Set<Grid> visited;

	public static void main(String[] args) {
		io = new Kattio(System.in, System.out);

		R = io.getInt();
		C = io.getInt();

		List<Edge> edgeList = new ArrayList<>(R*C);

		int[][] land = new int[R][C];
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				land[i][j] = io.getInt();
			}
		}

		int[][] dir = new int[][]{
			{0,1},{0,-1},{1,0},{-1,0}
		};

		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				for (int[] d : dir) {
					int nextR = i+d[0];
					int nextC = j+d[1];

					if (0<=nextR && nextR<R && 0<=nextC && nextC<C) {
						edgeList.add(new Edge(new Grid(i,j), new Grid(nextR, nextC), Math.max(land[i][j], land[nextR][nextC])));
					}
				}
			}
		}

		// create a Minimum Spanning-Tree using Kruskal's algo
		Collections.sort(edgeList);

		Set<Grid> mstVertices = new HashSet<>();
		Set<Edge> mstEdges = new HashSet<>();

		for (Edge e : edgeList) {
			if (!mstVertices.contains(e.u) || !mstVertices.contains(e.v)) {
				mstVertices.add(e.u); 
				mstVertices.add(e.v);				
				mstEdges.add(e);
			}
		}

		adjList = new HashMap<>(R*C);
		
		for (Edge e : mstEdges) {
			if (!adjList.containsKey(e.u)) {
				PriorityQueue<Edge> neighbours = new PriorityQueue<>();
				adjList.put(e.u, neighbours);
			}

			if (!adjList.containsKey(e.v)) {
				PriorityQueue<Edge> neighbours = new PriorityQueue<>();
				adjList.put(e.v, neighbours);
			}

			adjList.get(e.u).offer(new Edge(e.u, e.v, e.w));
			adjList.get(e.v).offer(new Edge(e.v, e.u, e.w));
		}

		io.println(mstEdges); io.flush();

		io.println("-----------");	

		for (Grid g : adjList.keySet()) {
			if (g.r == 0) { // start from the leftmost-side
				reached = false;
				visited = new HashSet<>();

				dfs(g, land[g.r][g.c]);
			}
		}

		io.println(best);
		io.close();
	}

	static void dfs(Grid g, int deepest) {
		if (reached == true) { return; }
		visited.add(g);

		io.println(g); io.flush();

		if (g.r == R-1) { // last-column
			io.println(deepest); io.flush();

			if (best > deepest) {
				best = deepest;
			}

			reached = true;
			return; // stop
		} else {
			PriorityQueue<Edge> neighbours = adjList.get(g);
			while (!neighbours.isEmpty() && reached == false) {
				Edge next = neighbours.poll();

				if (!visited.contains(next.v)) {
					if (next.w > deepest) {
						dfs(next.v, next.w);
					} else {
						dfs(next.v, deepest);
					}
				}
			}
		}
	}
}
*/

class Grid {
	public int r;
	public int c;

	public Grid(int r, int c) {
		this.r=r;
		this.c=c;
	}

	@Override
	public boolean equals(Object o) {
		Grid other = (Grid)o;

		if (other.r == this.r && other.c == this.c) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.r, this.c); /* REALLY USEFUL! remember to "import java.util.Objects" */ 
	}

	@Override
	public String toString() {
		return String.format("<%d,%d>", this.r, this.c);
	}
}

// an IntegerTriplet containing the weight of an edge from vertex u to v
class Edge implements Comparable<Edge> {
	Grid u;
	Grid v;
	int w;

	public Edge(Grid u, Grid v, int w) {
		this.u=u;
		this.v=v;
		this.w=w;
	}

	@Override
	public int compareTo(Edge other) {
		return this.w - other.w;
	}

	@Override
	public String toString() {
		return String.format("[%s->%s|%d]", u, v, w);
	}	
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
