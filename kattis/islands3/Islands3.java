/* PSEUDO-CODE

a[row][col] <- read in the grids
v[row][col] <- init a 2-dim visited array

result = 0 <- init result

for each row i:
	for each col j:
		if v[i][j] == false && a[i][j] == 'L': // if not yet visited and is LAND
			result++; // this is a new piece of land

			checkSurrounding(i, j)


print result

checkSurrounding(int i, int j):

	if v[i][j] == false: // if not visited
		v[i][j] = true; // mark as visited

		if a[i][j] == 'L' | 'C': // if the grid is LAND or CLOUD		
			checkSurrounding( i-1, j ) 				// check top
			checkSurrounding( (i+1 % row), j )		// check bottom
			checkSurrounding( i, j-1 )				// check left
			checkSurrounding( i, (j+1 % col) )		// check right

*/



import java.util.*;
import java.io.*;

public class Islands3 {
	public static int rows;
	public static int columns;
	public static char[][] grid;
	public static boolean[][] visited;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		rows = io.getInt();
		columns = io.getInt();

		grid = new char[rows][columns];

		char[] buffer;
		for (int i=0; i<rows; i++) {
			buffer = io.getWord().toCharArray();

			for (int j=0; j<columns; j++) {
				grid[i][j] = buffer[j];				
			}
		}

		visited = new boolean[rows][columns];	
		int result = 0;	

		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				if (visited[i][j] == false && grid[i][j] == 'L') {
					result++;

					checkSurrounding(i, j);
				}
			}
		}

		io.println(result);
		io.close();
	}

	static void checkSurrounding(int i, int j) {
		if (visited[i][j] == false) {
			visited[i][j] = true;

			if (grid[i][j] == 'L' || grid[i][j] == 'C') {
				if (i-1 >= 0) { checkSurrounding( i-1, j ); }		// check top
				if (i+1 < rows) { checkSurrounding( i+1, j ); }		// check bottom
				if (j-1 >= 0) { checkSurrounding( i, j-1 ); }		// check left
				if (j+1 < columns) { checkSurrounding( i, j+1 ); }	// check right
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
