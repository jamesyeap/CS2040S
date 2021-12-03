import java.util.*;
import java.io.*;

public class AMazing {
	static boolean[][] maze;
	static String[] directions;
	static Kattio io;

	public static void main(String[] args) {
		initDirection();
		io = new Kattio(System.in, System.out);

		maze = new boolean[10*2][10*2]; // because we don't know if we're starting from the middle
										// of the maze, or some corner, or anywhere-else

		int initialRow = 5; int initialCol = 5;

		if (move("up", initialRow-1, initialCol) == true) { return; }
		if (move("down", initialRow+1, initialCol) == true) { return; }
		if (move("left", initialRow, initialCol-1) == true) { return; }
		if (move("right", initialRow, initialCol+1) == true) { return; }
		
		io.println("no way out"); io.flush();


		io.close();
	}

	// returns true if an exit is found in this cell, or somewhere along the path
	//		in which this cell is a part of
	//	else, returns false
	static boolean move(String direction, int r, int c) {
		// io.println(String.format("--- checking cell <%d,%d> ---", r, c)); io.flush();
		printMaze();

		if (!(0 <= r && r < maze.length && 0 <= c && c < maze[0].length)) { // return false if this move is out of bounds
			// io.println("EXITING DUE TO OOB"); io.flush();

			return false;
		}

		if (maze[r][c] == true) { // return false if this cell has already been checked
			// io.println("EXITING AS THIS CELL HAS ALREADY BEEN CHECKED"); io.flush();

			return false; 
		}

		maze[r][c] = true; // mark this cell as checked

		String oppDirection = getOppositeDir(direction);

		io.println(direction); // go in this direction
		io.flush();

		String response = io.getWord();

		if (response.equals("wrong")) { System.exit(0); } // exit on error

		if (response.equals("solved")) {
			// System.exit(0); // exit successfully

			return true;			
		} else if (response.equals("wall")) {
			return false;
		} else {
			for (String dir : directions) {
				if (!dir.equals(oppDirection)) {
					int[] nextCell = getNextCoord(dir, r, c); 

					boolean pathFound = move(dir, r+nextCell[0], c+nextCell[1]);

					if (pathFound == true) { return true; }
				}
			}

			io.println(oppDirection); // go back
			io.flush();

			return false;
		}
	}

	static String getOppositeDir(String direction) {
		if (direction.equals("up")) {
			return "down";
		} else if (direction.equals("down")) {
			return "up";
		} else if (direction.equals("left")) {
			return "right";
		} else { // direction.equals("right")
			return "left";
		}
	}

	static void initDirection() {
		directions = new String[]{"up", "down", "left", "right"};
	}

	static int[] getNextCoord(String direction, int r, int c) {
		if (direction.equals("up")) {
			return new int[]{-1, 0};
		} else if (direction.equals("down")) {
			return new int[]{1, 0};
		} else if (direction.equals("left")) {
			return new int[]{0, -1};
		} else { // direction.equals("right")
			return new int[]{0, 1};
		}
	}

	static void printMaze() {
		System.out.println("-----------");
		for (int i=0; i<maze.length; i++) {
			System.out.println(Arrays.toString(maze[i]));
		}
		System.out.println("-----------");
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
