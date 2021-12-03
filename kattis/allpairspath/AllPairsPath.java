/*  NOTES:

	Have to use Double.POSITIVE_INFINITY and Double.NEGATIVE_INFINITY 
	for the negative-cycle detection loop to work properly(?)
*/

import java.util.*;
import java.io.*;

public class AllPairsPath {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int n = io.getInt(); // number of vertices
		int m = io.getInt(); // number of edges
		int q = io.getInt(); // number of queries

		double[][] adjMatrix; /* Floyd Warshall's needs an Adjacency-Matrix */ 

		while (!(n==0 && m==0 && q==0)) {

			// initialize the adjacent matrix for Floyd-Warshall's
			adjMatrix = new double[n][n];
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					if (i != j) {
						adjMatrix[i][j] = Double.POSITIVE_INFINITY;
					} else {
						adjMatrix[i][j] = 0.0;
					}
				}
			}

			// printAdjMatrix(adjMatrix);		

			// for nodes with direct-edges between each other, add the
			//		weight of the edges to the adjacent-matrix
			for (int i=0; i<m; i++) {
				int u = io.getInt(); // from vertex u
				int v = io.getInt(); // to vertex v
				int w = io.getInt(); // weight of this edge

				if (adjMatrix[u][v] > w) {
					adjMatrix[u][v] = w;
				}
			}

			// run standard Floyd-Warshall's
			for (int k=0; k<n; k++) {
				for (int i=0; i<n; i++) {
					for (int j=0; j<n; j++) {
						if ( (adjMatrix[i][k] != Double.POSITIVE_INFINITY)  // if there is some way FOUND to get from i to k
							&& 
							( adjMatrix[k][j] != Double.POSITIVE_INFINITY)) // if there is some way FOUND to get from k to j							 
						{
							double throughK = adjMatrix[i][k] + adjMatrix[k][j];

							if (adjMatrix[i][j] > throughK) {
								adjMatrix[i][j] = throughK;
							}
						}
					}
				}
			}

			// run standard Floyd-Warshall's AGAIN (to check for any NEGATIVE-cycles)
			for (int k=0; k<n; k++) {
				for (int i=0; i<n; i++) {
					for (int j=0; j<n; j++) {
						if ( (adjMatrix[i][k] != Double.POSITIVE_INFINITY)  // if there is some way FOUND to get from i to k
							&& 
							( adjMatrix[k][j] != Double.POSITIVE_INFINITY)) // if there is some way FOUND to get from k to j							 
						{
							double throughK = adjMatrix[i][k] + adjMatrix[k][j];

							if (adjMatrix[i][j] > throughK) {
								adjMatrix[i][j] = Double.NEGATIVE_INFINITY;
							}
						}
					}
				}
			}			

			// process-queries
			for (int i=0; i<q; i++) {
				int from = io.getInt();
				int to = io.getInt();

				if (adjMatrix[from][to] == Double.POSITIVE_INFINITY) {
					io.println("Impossible");					
				} else if (adjMatrix[from][to] == Double.NEGATIVE_INFINITY) { 
					io.println("-Infinity");
				} else {
					io.println(Math.round(adjMatrix[from][to]));
				}
			}


			n = io.getInt(); m = io.getInt(); q = io.getInt();
		}

		io.close();
	}

	static void printAdjMatrix(double[][] adjMatrix) {
		for (int i=0; i<adjMatrix.length; i++) {
			System.out.println(Arrays.toString(adjMatrix[i]));
		}
		System.out.println();	
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