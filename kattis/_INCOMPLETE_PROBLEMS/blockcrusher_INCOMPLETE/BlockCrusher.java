/* STATUS: DONT THROW AWAY YET!!! */

/* PSEUDO-CODE

shortestDist <- +INF
shortestCrack <- null

D[] <- distance-array
p[] <- predecessor-array

for every grid in the TOP row, g:

	initSSSP(); // init D[] and p[]
	
	D[g] = 0;
	pq.offer(new Edge(0, g));

	crackEnd = -1; 

	while (!pq.isEmpty()):
		(d, u) <- pq.poll();

		if u.row == lastRow:
			crackEnd = u;
			break;

		if d == D[u]:
			for each vertex adjacent to u, v:
				if (D[v] > D[u] + d):
					D[v] = D[u] + d; // relax-operation 
					p[v] = u; // update-predecessor
			
	
	if (D[crackEnd] < shortestDist):
		shortestDist = D[crackEnd]
		shortestCrack = getPath(crackEnd); // get the actual-path from crackEnd -> g

*/

import java.util.*;
import java.io.*;

public class BlockCrusher {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int h = io.getInt();
		int w = io.getInt();

		int[][] possibleDirections = new int[][]{
			{-1, 0},
			{-1, -1},
			{-1, 1},
			{0, 1},
			{0, -1},
			{1, 0},
			{1, -1},
			{1, 1}
		};

		int INF = 1000000000;

		while (!(h==0 && w==0)) {			
			int[][] block = new int[h][w];

			int V =  h*w;
			List<Grid> gridList = new ArrayList<>(V);
			List<Edge>[][] adjList = new List<>[V][V];

			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					block[i][j] = new io.getInt();

					gridList.add(new Grid(i, j, block[i][j]));

					List<Edge> neighbours = new ArrayList<>(8);
					adjList[i][j] = neighbours;
				}
			}

			for (int i=0; i<gridList.size(); i++) {
				Grid g = gridList.get(i);

				List<Edge> neighbours = adjList[g.r][g.c];

				for (int[] dir : possibleDirections) {
					int nextR = grid.r+dir[0];
					int nextC = grid.c +dir[1];

					if (0<=nextR && nextR<h && 0<= nextC && nextC<w) { // if within-bounds
						neighbours.add(nextR, nextC, block[nextR][nextC]);
					}
				}
			}

			int shortestDist = INF;
			List<Integer[]> shortestPath = new ArrayList<>();

			// run modified-Dijkstra's on the TOP row
			for (int k=0; k<w; k++) {

				// initSSSP() step
				int[][] D = new int[V][V];
				for (int i=0; i<D.length; i++) {
					Arrays.fill(D[i], INF);
				}

				Integer[][][] p = new Integer[V][V][2];				

				// init a MIN-heap
				PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2)-> e2.str - e1.str);

				D[0][k] = block[0][k]; // start from the kth grid of the TOP row
				pq.offer(new Edge(0, k, D[0][k]));

				Integer[] crackEndsAt;

				while (!pq.isEmpty()) {
					Edge e = pq.poll();					

					if (e.str == D[e.r][e.c]) {
						if (e.r == h-1) {
							crackEndsAt = new Integer[]{e.r, e.c};
							break;
						}

						for (Edge nextE : adjList[e.r][e.c]) {
							if (D[nextE.r][nextE.c] > D[e.r][e.c] + e.str) {
								D[nextE.r][nextE.c] = D[e.r][e.c] + e.str;
								p[nextE.r][nextE.c] = new Integer[]{e.r, e.c};

								pq.offer(nextE.r, nextE.c, D[nextE.r][nextE.c]);								
							}
						}
					}
				}

				if (D[crackEndsAt[0]][crackEndsAt[1]] < shortestDist) {
					shortestDist = D[crackEndsAt[0]][crackEndsAt[1]];
					shortestPath = "TODO"; 
				}
			}

			io.println(shortestDist);


			h = io.getInt();
			w = io.getInt();
		}
	}
}

class Grid {
	public int r;
	public int c;
	public int str;

	public Grid(int r, int c, int str) {
		this.r = r; 
		this.c = c;
		this.str = str;
	}
}

class Edge {
	public int r;
	public int c;
	public int str;

	public Edge(int r, int c, int str) {
		this.r = r; 
		this.c = c;
		this.str = str;
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