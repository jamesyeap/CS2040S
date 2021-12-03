import java.util.*;
import java.io.*;
import java.awt.*;

public class MillionaireMadness {
	public static int M;
	public static int N;
	public static int[][] g;
	public static HashMap<Point, Boolean> visited;
	public static PriorityQueue<Edge> pq;
	public static ArrayList<Edge> path;
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		M = io.getInt(); // length
		N = io.getInt(); // width

		g = new int[M][N]; // initialize an empty array to record vault data
		visited = new HashMap<>(M*N); // initialise a visited hashmap

		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				g[i][j] = io.getInt();

				Point pos = new Point(j,i);
				visited.put(pos, false);
			}
		}

		pq = new PriorityQueue<>();
		path = new ArrayList<>();
		Point startingPoint = new Point(0, 0); 
		Point endingPoint = new Point(N-1, M-1);

		// start from north-west corner
		Edge e = new Edge(startingPoint, startingPoint, 0);
		pq.add(e);

		while (!pq.isEmpty()) {
			e = pq.poll(); // get the edge with the smallest-weight

			// e: u->v
			if (visited.get(e.v) == false) { // if v is not visited yet
				visited.put(e.v, true); // mark v as visited
				path.add(e);	// this is the shortest path from u->v (by cut-property)

				if (e.v.equals(endingPoint)) {
					break;
				}

				enqueueEdges(e.v);
			}
		}

		int result = 0;
		for (Edge x : path) {
			if (x.weight > result) {
				result = x.weight;
			}
		}

		io.println(result);
		io.close();
	}

	static void enqueueEdges(Point p) { // calculates the cost of going top, bottom, left, right of position i,j and enqueues it as an edge
		int i = (int)p.getY();
		int j = (int)p.getX();

		if (i-1 >= 0) { // check top
			int topCost = g[i-1][j] - g[i][j];

			Point pTop = new Point(j, i-1);
			Edge e = new Edge(p, pTop, Math.max(0, topCost));
			pq.offer(e);
		}

		if (i+1 < M) { // check bottom
			int bottomCost = g[i+1][j] - g[i][j];

			Point pBottom = new Point(j, i+1);
			Edge e = new Edge(p, pBottom, Math.max(0, bottomCost));
			pq.offer(e);
		}

		if (j-1 >= 0) { // check left
			int leftCost = g[i][j-1] - g[i][j];			

			Point pLeft = new Point(j-1, i);
			Edge e = new Edge(p, pLeft, Math.max(0, leftCost));
			pq.offer(e);
		}

		if (j+1 < N) { // check right
			int rightCost = g[i][j+1] - g[i][j];

			Point pRight = new Point(j+1, i);
			Edge e = new Edge(p, pRight, Math.max(0, rightCost));
			pq.offer(e);
		}
	}
}

class Edge implements Comparable<Edge> {
	public Point u;
	public Point v;
	public int weight;

	public Edge(Point u, Point v, int weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge e) {
		return this.weight - e.weight;
	}

	@Override
	public String toString() {
		return String.format("from <%d,%d> to <%d,%d> cost %d", (int)u.getY(), (int)u.getX(), (int)v.getY(), (int)v.getX(), weight);
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

