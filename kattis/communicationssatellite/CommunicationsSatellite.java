import java.util.*;
import java.io.*;
import java.awt.geom.Point2D;

public class CommunicationsSatellite {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int n = io.getInt(); // number of dish-antennas

		List<Dish> dishes = new ArrayList<>(n);
		for (int i=0; i<n; i++) {
			double x = io.getDouble();
			double y = io.getDouble();
			double radius = io.getDouble();

			Point2D.Double coord = new Point2D.Double(x, y);
			dishes.add(new Dish(coord, radius));
		}

		UFDS mstVertices = new UFDS(n);
		List<Edge> edgeList = new ArrayList<>(n*n);

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (i != j) {
					Dish d1 = dishes.get(i);
					Dish d2 = dishes.get(j);
					double distance = d1.coord.distance(d2.coord) - d1.radius - d2.radius;

					if (distance == 0) {
						mstVertices.merge(i, j);
					} else {
						Edge e = new Edge(i, j, distance);
						edgeList.add(e);
					}
				}
			}
		}

		/* ----- run Kruskal's MST ----- */
		Collections.sort(edgeList);

		double totalLen = 0;

		for (Edge e : edgeList) {
			if (mstVertices.nSets == 1) {
				break;
			}

			if (!mstVertices.isSameSet(e.p1, e.p2)) {
				mstVertices.merge(e.p1, e.p2);
				totalLen += e.distance;
			}
		}

		io.println(totalLen);
		io.close();
	}
}

class Dish {
	public Point2D.Double coord;
	public double radius;

	public Dish(Point2D.Double coord, double radius) {
		this.coord = coord;
		this.radius = radius;
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
			nSets -= 1;
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