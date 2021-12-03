/* TRASHED 

Brudder, why are you using Floyd-Warshall's here?

*/

import java.util.*;
import java.io.*;

public class BlockCrusher_TRASHED1 {
	static int INF = 1000000000;

	static Kattio io;

	static List<Grid> gridList;
	static int[][] blockMatrix;
	static int[][] distanceMatrix;
	static int[][] parentMatrix;

	public static void main(String[] args) {
		io = new Kattio(System.in, System.out);

		int h = io.getInt(); // get height (number of rows)
		int w = io.getInt(); // get width (number of columns)

		io.println(String.format("%d %d", h, w));

		while (!(h==0 && w==0)) {
			int V = h*w;

			gridList = new ArrayList<>(V);			

			// get matrix-representation of the block
			blockMatrix = new int[h][w];
			for (int i=0; i<h; i++) {
				String[] row = io.getWord().split("");				

				for (int j=0; j<w; j++) {	
					blockMatrix[i][j] = Integer.parseInt(row[j]);				

					gridList.add(new Grid(gridList.size(), i, j, blockMatrix[i][j]));
				}				

				// io.println(); io.flush();
			}

			// create a distance-matrix for Floyd-Warshall's
			distanceMatrix = new int[V][V]; // adjMatrix[|V|][|V|]
			for (int i=0; i<gridList.size(); i++) {
				Grid g1 = gridList.get(i);

				for (int j=0; j<gridList.size(); j++) {

					if (i != j) {
						Grid g2 = gridList.get(j);

						if (Math.abs(g1.r - g2.r) <= 1 && Math.abs(g1.c - g2.c) <= 1) {
							distanceMatrix[i][j] = g2.str;
						} else {
							distanceMatrix[i][j] = INF;
						}
					} else {
						distanceMatrix[i][j] = 0;
					}
				}
			}

			// create an additional parent-matrix for Floyd-Warshall's (Variant 1)
			parentMatrix = new int[V][V];
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					parentMatrix[i][j] = i;
				}
			}

			printMatrix(blockMatrix);
			// printMatrix(distanceMatrix);
			// printMatrix(parentMatrix);

			// run Floyd-Warshall's
		    for (int k=0; k < V; k++) {
		    	Grid b = gridList.get(k);

		    	for (int i=0; i < V; i++) {
		    		Grid a = gridList.get(i);

		    		for (int j=0; j < V; j++) {
		    			Grid c = gridList.get(j);

		    			if (distanceMatrix[a.index][b.index]+distanceMatrix[b.index][c.index] < distanceMatrix[a.index][c.index]) {
		        			distanceMatrix[a.index][c.index] = distanceMatrix[a.index][b.index]+distanceMatrix[b.index][c.index];
		          			parentMatrix[a.index][c.index] = parentMatrix[b.index][c.index];
		        		}
		       		}
		    	}
		    }

		    printMatrix(distanceMatrix);


		    h = io.getInt();
		    w = io.getInt();
		}

		io.close();
	}

	static void printMatrix(int[][] matrix) {
		io.println("================================================");

		for (int i=0; i<matrix.length; i++) {
			io.println(Arrays.toString(matrix[i]));
		}

		io.println("================================================");
	}
}

class Grid {
	public int index;
	public int r;
	public int c;
	public int str;

	public Grid(int index, int r, int c, int str) {
		this.index = index;
		this.r = r;
		this.c = c;
		this.str = str;
	}

	@Override
	public boolean equals(Object o) {
		Grid other = (Grid) o;

		return (this.r == other.r) && (this.c == other.c);
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