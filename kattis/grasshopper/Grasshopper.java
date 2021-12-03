import java.util.*;
import java.io.*;

public class Grasshopper {
	static int[][] moves;

	public static void main(String[] args) {
		initMoves();

		Kattio io = new Kattio(System.in, System.out);

		while (true) {
			if (!io.hasMoreTokens()) {
				break;
			}

			int r = io.getInt(); int c = io.getInt();
			int gR = io.getInt(); int gC = io.getInt();
			int cR = io.getInt(); int cC = io.getInt();

			int board[][] = new int[r+1][c+1];
			boolean visited[][] = new boolean[r+1][c+1];		
			
			Queue<IntegerPair> q = new LinkedList<>();
			q.offer(new IntegerPair(gR, gC, 0));
			visited[gR][gC] = true;

			boolean cloverReached = false;

			while (!q.isEmpty()) {
				IntegerPair curr = q.poll();

				if (curr.r == cR && curr.c == cC) {
					io.println(curr.hops);
					cloverReached = true;

					break;
				}

				for (int[] m : moves) {
					int nextR = curr.r + m[0]; int nextC = curr.c + m[1];

					if (1<=nextR && nextR<=r && 1<=nextC && nextC<=c && visited[nextR][nextC] == false) { // check that its within bounds
						visited[nextR][nextC] = true;

						q.offer(new IntegerPair(nextR, nextC, curr.hops+1));
					}
				}			
			}

			if (cloverReached == false) {
				io.println("Impossible");
			}
		}
		io.close();

	}

	static void initMoves() {
		// {rOffset, cOffset}
		moves = new int[][]
		{ 
			{2, -1}, {1, -2},
			{-1, -2}, {-2, -1},
			{-2, 1}, {-1, 2},
			{1, 2}, {2, 1}
		};				
	}
}

class IntegerPair {
	public int r;
	public int c;
	public int hops;

	public IntegerPair(int r, int c, int hops) {
		this.r = r;
		this.c = c;
		this.hops = hops;
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

