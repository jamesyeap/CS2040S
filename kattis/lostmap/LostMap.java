import java.util.*;
import java.io.*;

public class LostMap {
	public static PriorityQueue<Edge> edgeList;
	public static UFDS mst;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int N = io.getInt(); // number of villages		

		edgeList = new PriorityQueue<>(); // an edge-list; sorted from lowest-to-highest edge weights
		mst = new UFDS(N);

		Edge currEdge;
		Integer distance;
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				distance = io.getInt();

				if (j > i) {
					currEdge = new Edge(i, j, distance);
					edgeList.offer(currEdge);
				}
			}
		}

		while (!edgeList.isEmpty()) {
			currEdge = edgeList.poll();

			if (!mst.isSameSet(currEdge.u, currEdge.v)) {
				mst.merge(currEdge.u, currEdge.v);

				io.println(String.format("%d %d", currEdge.u, currEdge.v));
			}
		}

		io.close();
	}
}

class Edge implements Comparable<Edge> {
	public int u;
	public int v;
	public int distance;

	public Edge(int u, int v, int distance) {
		this.u = u;
		this.v = v;
		this.distance = distance;
	}

	@Override
	public int compareTo(Edge o) {
		return this.distance - o.distance;
	}

	@Override
	public String toString() {
		return String.format("[%d] -> [%d] | %d", u, v, distance);
	}
}

class UFDS {
	int[] p;

	public UFDS(int nElems) {
		p = new int[nElems+1]; // 1-indexed

		for (int i=1; i<=nElems; i++) {
			p[i] = i;
		}
	}

	public boolean isSameSet(int a, int b) {
		return getParent(a) == getParent(b);
	}

	public int getParent(int a) {
		if (p[a] == a) { return a; }

		p[a] = getParent(p[a]);

		return p[a];
	}

	public void merge(int a, int b) {
		if (!isSameSet(a, b)) {
			p[getParent(a)] = p[getParent(b)];
		}
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
