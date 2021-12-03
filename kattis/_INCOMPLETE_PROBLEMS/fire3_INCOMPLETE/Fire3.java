/* 
STATUS: Time-Limit Exceeded

POSSIBLE REASONS: dunno man i am confuseddd
*/

import java.util.*;
import java.io.*;

public class Fire3 {
	static int R;
	static int C;
	static char[][] maze;
	static PriorityQueue<Position> pq;
	static int[][] directions;

	static int nJoes; // number of possible-grids occupied by Joe (a little weird)
	static boolean joeDead;
	static boolean joeEscaped; static int joeEscapedTime;

	public static void main(String[] args) {
		initDirections();
		Kattio io = new Kattio(System.in, System.out);

		R = io.getInt(); // get number of rows
		C = io.getInt(); // get number of columns

		// create matrix-representation of the maze
		maze = new char[R][C];
		for (int i=0; i<R; i++) {
			char[] line = io.getWord().toCharArray();

			for (int j=0; j<C; j++) {
				maze[i][j] = line[j];
			}
		}

		// printMaze(); // for debugging		

		// run multi-source BFS

		/* PREPARATION STEPS */
		pq = new PriorityQueue<>();
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (maze[i][j] == 'J' || maze[i][j] == 'F') {
					pq.offer(new Position(0, i, j, maze[i][j]));
				}
			}
		}
		nJoes=1;

		while (!pq.isEmpty() && joeEscaped == false && joeDead == false) {
			// printMaze();

			Position p = pq.poll();

			generateMoves(p.time, p.row, p.col, p.type);
		}

		if (joeEscaped == true) {
			io.println(joeEscapedTime);
		} else {
			io.println("IMPOSSIBLE");
		}

		io.close();

	}

	static void initDirections() {
		directions = new int[][]{
			{0, -1},
			{-1, 0},
			{0, 1},
			{1, 0}
		};
	}	

	static void printMaze() {
		for (int i=0; i<maze.length; i++) {
			System.out.println(Arrays.toString(maze[i]));
		}

		System.out.println(pq);
		System.out.println();
	}

	static void generateMoves(int time, int r, int c, char type) {
		if (joeDead == true || joeEscaped == true) {
			return; // no point checking, since Joe is dead OR has managed to escape
		}

		// try going LEFT, UP, RIGHT, DOWN

		if (type == 'F') { // TODO: some fire stuff yeahboi

			for (int[] dir : directions) {
				int nextR = r+dir[0];
				int nextC = c+dir[1];

				if (0<=nextR && nextR<R && 0<=nextC && nextC<C) { // check that the move is within-limits of the maze
					if (maze[nextR][nextC] != '#') { // check that the fire is not moving INSIDE a wall (because it can't)
						if (maze[nextR][nextC] == 'J') { // if the fire is moving to Joe next
							maze[nextR][nextC] = 'F'; // consume Joe
							nJoes--;

							if (nJoes == 0) {
								joeDead = true;

								break; // stop processing, since Joe is dead
							} else {
								pq.offer(new Position(time+1, nextR, nextC, type)); 
							}
						} else if (maze[nextR][nextC] == '.') { // if the fire is moving into an empty-space next
							maze[nextR][nextC] = 'F'; // occupy the empty-space
							pq.offer(new Position(time+1, nextR, nextC, type));
						} else {
							// if the fire is moving into a grid that is already occupied by another fire, 
							// 		don't need to do anything(?)
						}
					}
				}
			}

		} else { // TODO: some joe stuff yeahboiiiiii
			for (int[] dir : directions) {
				if (maze[r][c] == 'F') { // if this position that Joe is in has been consumed by the fire, no moves are possible from this position
					break;
				}

				int nextR = r+dir[0];
				int nextC = c+dir[1];

				if (0<=nextR && nextR<R && 0<=nextC && nextC<C) { // check that the move is within-limits of the maze
					if (maze[nextR][nextC] != '#') { // check that Joe is not moving INSIDE a wall (because he can't)
						if (maze[nextR][nextC] != 'F') { // check that Joe is not moving INSIDE a fire (because he'd die)
							maze[nextR][nextC] = 'J';
							nJoes++;

							pq.offer(new Position(time+1, nextR, nextC, type));
						}
					}
				} else {
					joeEscaped = true;
					joeEscapedTime = time+1;

					break; // stop processing, since Joe has escaped
				}
			}

		}


	}
}

class Position implements Comparable<Position> {
	int time;
	int row;
	int col;
	char type; // 'J' for Joe, 'F' for fire

	public Position(int time, int row, int col, char type) {
		this.time = time;
		this.row = row;
		this.col = col;
		this.type = type;
	}

	@Override
	public int compareTo(Position other) {
		if (this.time > other.time) {
			return 1; // process earlier moves first
		} else if (this.time < other.time) {
			return -1;
		} else {
			if (this.type == 'J' && other.type == 'F') {
				return -1; // process 'J' first -> so return -1 to prioritise this in the min-heap
			} else if (this.type == 'F' && other.type == 'J') {
				return 1;
			} else {
				return 0; // equal priority for if same-type
			}
		}
	}

	@Override
	public String toString() {
		return String.format("%c at <%d, %d> at time=%d", type, row, col, time);
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