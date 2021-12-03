import java.util.*;
import java.io.*;
import java.awt.geom.Point2D;

public class ArcticNetwork {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int nTestCases = io.getInt();

		for (int testCase=0; testCase < nTestCases; testCase++) {
			int s = io.getInt(); // number of satellite channels
			int p = io.getInt(); // number of outposts

			/* get coordinates of all outposts */
			List<Point2D.Double> outposts = new ArrayList<>(p);			
			for (int i=0; i<p; i++) {
				double x = io.getDouble(); double y = io.getDouble();
				outposts.add(new Point2D.Double(x, y));
			}

			/* construct a COMPLETE graph for all the outposts */
			// weights of the edges are the distances between each pair of outposts
			List<Edge> edgeList = new ArrayList<>(p*p);			
			for (int i=0; i<outposts.size(); i++) {					
				for (int j=0; j<outposts.size(); j++) {
					if (i != j) {
						double distance = outposts.get(i).distance(outposts.get(j)); // get distance between the two outposts
						Edge e = new Edge(i, j, distance);

						edgeList.add(e);
					}
				}								
			}

			/* run Kruskal's MST to PARTIAL-COMPLETION */
			
			//	note: Prim's MST may not work here -> if you arbitrarily start from a
			//			vertex that is extremely far out from the others,
			//			you will add that edge to the MST, even when you should
			//			use a satellite-channel instead (aka NOT adding that edge to the MST)

			Collections.sort(edgeList);
			List<Edge> mstEdges = new ArrayList<>(p);
			UFDS mstVertices = new UFDS(p);

			for (Edge e : edgeList) {
				if (mstEdges.size() == p-s) {
					break;
				}

				if (!mstVertices.isSameSet(e.p1, e.p2)) {
					mstVertices.merge(e.p1, e.p2);
					mstEdges.add(e);
				}
			}

			io.println(String.format("%.2f", mstEdges.get(mstEdges.size()-1).distance));
		}

		io.close();		
	}
}

class Edge implements Comparable<Edge> {
	public int p1;
	public int p2;
	public double distance;

	public Edge(int p1, int p2, double distance) {
		this.p1 = p1;
		this.p2 = p2;
		this.distance = distance;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.distance >= o.distance) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return String.format("%d<->%d: %f", p1, p2, distance);
	}
}

class UFDS {
	public int[] p;
	public int nSets; 

	public UFDS(int nElems) {
		p = new int[nElems+1];
		for (int i=1; i<=nElems; i++) {
			p[i] = i;
		}

		int nSets = nElems;
	}

	public void merge(int a, int b) {
		if (!isSameSet(a, b)) {
			p[getParent(a)] = getParent(b);
		}
	}

	public int getParent(int a) {
		if (p[a] == a) { return a; }

		p[a] = getParent(p[a]);
		return p[a];
	}

	public boolean isSameSet(int a, int b) {
		return getParent(a) == getParent(b);
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