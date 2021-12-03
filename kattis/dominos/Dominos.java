import java.util.*;
import java.io.*;

public class Dominos {
	public static HashMap<Integer, List<Integer>> adjList;
	public static boolean[] visited;
	public static boolean[] toppled;

	public static List<Integer> topoSorted;

	public static Integer nDominos;
	public static Integer nLines;
	public static Integer d1;
	public static Integer d2;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		adjList = new HashMap<>(100000);

		int nCases = io.getInt(); // number of test-cases to follow

		for (int i=0; i<nCases; i++) {
			nDominos = io.getInt();
			nLines = io.getInt();						

			for (int j=0; j<nLines; j++) {
				d1 = io.getInt();
				d2 = io.getInt();

				if (!adjList.containsKey(d1)) {
					List<Integer> lst = new ArrayList<>();
					adjList.put(d1, lst);
				}

				adjList.get(d1).add(d2); // add an outgoing edge from d1 to d2						
			}

			/* ------------- use TOPOSORT (DFS-version) --------------------------------------------------------------- */ 		
			
			topoSorted = new ArrayList<>(nDominos);
			visited = new boolean[nDominos+1]; // 1-indexed

			for (int j=1; j<=nDominos; j++) {

				if (visited[j] == false) {
					dfsTopoSort(j);
				}
			}

			Collections.reverse(topoSorted);

			/* -------------------------------------------------------------------------------- END OF SECTION ------- */

			/* ------------  TOPPLE THE DOMINOS ---------------------------------------------------------------------- */

			toppled = new boolean[nDominos+1]; // 1-indexed
			int result = 0;

			for (Integer v : topoSorted) {
				if (toppled[v] == false) {					
					result++;

					topple(v);
				}
			}

			/* -------------------------------------------------------------------------------- END OF SECTION ------- */

			io.println(result);

			adjList.clear();			
		}

		io.close();
	}

	static void dfsTopoSort(int u) {
		if (visited[u] == false) {
			visited[u] = true;

			List<Integer> adjV = adjList.get(u);

			if (adjV != null) {
				for (Integer v : adjV) {
					if (visited[v] == false) {
						dfsTopoSort(v);
					}
				}
			}

			topoSorted.add(u);
		}
	}

	static void topple(Integer u) {		
		if (toppled[u] == false) {
			toppled[u] = true;

			List<Integer> adjV = adjList.get(u);

			if (adjV != null) {
				for (Integer v : adjV) {
					topple(v);
				}
			}
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
