import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int nDatasets = io.getInt();

		for (int dataset=0; dataset<nDatasets; dataset++) {
			int n = io.getInt(); // number of junctions
			int m = io.getInt(); // number of roads;

			List<Edge> edgeList = new ArrayList<>(m);

			for (int i=0; i<m; i++) {
				int u = io.getInt();
				int v = io.getInt();
				int cost = io.getInt();

				edgeList.add(new Edge(u, v, cost));				
			}

			// sort edge-list in ASCENDING order; LEAST-costly to MOST-costly roads
			Collections.sort(edgeList, (u,v)-> u.cost-v.cost);			

			UFDS mstVertices = new UFDS(n);
			List<Edge> mstEdges = new ArrayList<>(n-1);	
			List<Edge> excludedEdges = new ArrayList<>(m);		

			int nEdgesAdded = 0;
			for (int i=edgeList.size()-1; i>=0; i--) {
				Edge e = edgeList.get(i);

				if (mstEdges.size() < n-1) {					
					if (!mstVertices.isSameSet(e.u, e.v)) {
						mstVertices.merge(e.u, e.v);
						mstEdges.add(e);
					} else {
						excludedEdges.add(e);
					}
				} else {
					excludedEdges.add(e);
				}
			}			

			int minCost = 0;
			for (Edge e : excludedEdges) {
				minCost += e.cost;
			}			

			io.println(minCost);
		}

		io.close();
	}
}

// bi-directed edge
class Edge {
	public int u;
	public int v;
	public int cost;

	public Edge(int u, int v, int cost) {
		this.u = u;
		this.v = v;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return String.format("<%d-%d> %d", u, v, cost);
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
		return findSet(a) == findSet(b);
	}

	public int findSet(int a) {
		if (p[a] == a) { return a; }

		p[a] = findSet(p[a]);
		return p[a];
	}

	public void merge(int a, int b) {
		if (!isSameSet(a, b)) {
			int setA = findSet(a);
			int setB = findSet(b);

			p[setB] = setA;
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