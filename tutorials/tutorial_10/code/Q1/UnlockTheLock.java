import java.util.*;
import java.io.*;

public class UnlockTheLock {
	public static List<Integer> buttons;
	public static HashMap<Integer, List<Integer>> adjList;
	public static Queue<Integer> q;
	public static int[] visited;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		Integer L, U, R;
		L = io.getInt(); U = io.getInt(); R = io.getInt();

		buttons = new ArrayList<Integer>(10000);
		adjList = new HashMap<Integer, List<Integer>>(10000);
		q = new LinkedList<Integer>();
		visited = new int[10000]; Arrays.fill(visited, -1);

		int currTestCase = 1;
		while ( !(L == 0 && U == 0 && R == 0) ) {
			// read in all button values
			for (int i=0; i<R; i++) {
				buttons.add(io.getInt());
			}

			// start from the lock
			List<Integer> adjV = new ArrayList<>();
			adjList.put(L, adjV);
			q.offer(L);
			visited[L] = L;

			boolean found = false;

			Integer curr;
			Integer next;
			while (!q.isEmpty()) {
				curr = q.poll();

				for (Integer value : buttons) {
					next = (curr + value) % 10000;

					if (next.equals(U)) { visited[U] = curr; found=true; break; } // the unlocked-value has been reached					

					if (visited[next] == -1) {
						visited[next] = curr; // mark this adjacent-vertex as having been visited by the current vertex

						// io.println(String.format("U: %d, next: %d | U == next: %b", U, next, U.equals(next)));						

						q.offer(next);
					}
				}

				if (found == true) { break; }
			}

			if (found == true) {
				// count path-length from source (L) to destination (U)
				int len = 1;

				Integer d = visited[U];
				while (!d.equals(L)) {
					len++;

					// io.println(String.format("%d -> %d", d, visited[d]));

					d = visited[d];
				}

				io.println(String.format("Case %d: %d", currTestCase, len));
			} else {
				io.println(String.format("Case %d: Permanently Locked", currTestCase));
			}

			currTestCase++;

			buttons.clear();
			adjList.clear();
			q.clear();
			visited = new int[10000]; Arrays.fill(visited, -1);
			L = io.getInt(); U = io.getInt(); R = io.getInt();			
		}

		io.close();
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