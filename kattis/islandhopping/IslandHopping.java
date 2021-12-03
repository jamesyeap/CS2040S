import java.util.*;
import java.io.*;
import java.awt.Point;
import java.awt.geom.Point2D;

public class IslandHopping {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int nTestCases = io.getInt(); // get number of test-cases

		for (int testCase = 0; testCase < nTestCases; testCase++) {
			// use Prim's MST algo to generate an MST, then 
			//		sum the weights of all the edges

			int m = io.getInt(); // get number of islands

			List<Point2D.Double> islands = new ArrayList<>(m);
			Set<Point2D.Double> connected = new HashSet<>(m);
			HashMap<Point2D.Double, List<Edge>> adjList = new HashMap<>(m);

			for (int i=0; i<m; i++) {
				double x = io.getDouble(); double y = io.getDouble();
				islands.add(new Point2D.Double(x, y));
			}

			/* ------------- create a complete-graph with the distances between all edges ------------- */
			for (Point2D.Double i : islands) {
				List<Edge> neighbours = new ArrayList<>(m-1);				

				for (Point2D.Double j : islands) {					
					if (!i.equals(j)) {
						neighbours.add(new Edge(i, j));
					}
				}

				adjList.put(i, neighbours);
			}

			/* ------------- run Prim's algo on this complete graph; summing the weights of the edges that we include in the MST as we go along ------------- */
			
			// by convention, start from the "first" island
			PriorityQueue<Edge> pq = new PriorityQueue<>();					
			pq.offer(new Edge(islands.get(0), islands.get(0)));

			double totalDist = 0;

			while (connected.size() < m && !pq.isEmpty()) {
				Edge e = pq.poll();

				if (!connected.contains(e.i2)) {
					connected.add(e.i2);
					totalDist += e.dist;

					List<Edge> neighbours = adjList.get(e.i2);
					for (Edge next : neighbours) {
						if (!connected.contains(next.i2)) {
							pq.offer(next);
						}
					}
				}
			}

			io.println(totalDist);
		}

		io.close();
	}

	static double getDistance(Point2D.Double a, Point2D.Double b) {
		return Point2D.distance(a.getX(), a.getY(), b.getX(), b.getY());
	}
}

class Edge implements Comparable<Edge> {
	Point2D.Double i1;
	Point2D.Double i2;
	double dist;

	public Edge(Point2D.Double i1, Point2D.Double i2) {
		this.i1 = i1;
		this.i2 = i2;
		this.dist = Point2D.distance(i1.getX(), i1.getY(), i2.getX(), i2.getY());
	}

	@Override
	public int compareTo(Edge o) {
		if (this.dist >= o.dist) {
			return 1;
		} else {
			return -1;
		}
	}

}

// class UFDS {
// 	public int nSets; // number of disjoint sets
// 	public int[] p; // 1-indexed array of parent-elements

// 	public UFDS(int numElems) {
// 		nSets = numElems;

// 		p = new int[numElems+1];
// 		for (int i=1; i<=numElems; i++) { p[i] = i; } // every element is its own parent, initially
// 	}

// 	private boolean isSameSet(int a, int b) {
// 		return getParent(a) == getParent(b);
// 	}

// 	private int getParent(int a) {
// 		if (p[a] == a) { return a; }

// 		p[a] = getParent(p[a]); // path-compression

// 		return p[a];
// 	}

// 	private void merge(int a, int b) {
// 		if (!isSameSet(a, b)) {
// 			p[getParent(a)] = p[getParent(b)];
// 		}
// 	}
// }

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
