import java.util.*;
import java.io.*;

public class VirtualFriends {
	public static Trie m;
	public static int[] parent;
	public static int[] size;
	public static int curr;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int nTC = io.getInt(); // number of testcases		

		m = new Trie();
		parent = new int[10000];
		size = new int[10000];
		curr = 0;

		String[] pair;

		for (int i=0; i<nTC; i++) { // for each testcase
			int nFS = io.getInt(); // number of friendships made in this testcase			

			for (int j=0; j<nFS; j++) {				
				String f1 = io.getWord(); // get the names of the 2 friends
				String f2 = io.getWord();

				if (!m.containsKey(f1)) { 					
					m.put(f1, curr);
					parent[curr] = curr;
					size[curr] = 1;

					curr++;
				}

				if (!m.containsKey(f2)) { 					
					m.put(f2, curr);
					parent[curr] = curr;
					size[curr] = 1;

					curr++;
				}

				int size = merge(m.get(f1), m.get(f2));

				io.println(size);
			}

			// reset
			m = new Trie();
			curr = 0;
			parent = new int[10000];
			size = new int[10000];			
		}

		io.close();
	}

	static int getParent(int x) {
		if (parent[x] == x) { return x; }

		parent[x] = getParent(parent[x]);

		return parent[x];
	}

	static int merge(int x, int y) {
		if (getParent(x) == getParent(y)) {
			return size[getParent(x)];
		}

		if (size[getParent(x)] > size[getParent(y)]) {
			size[getParent(x)] += size[getParent(y)];
			parent[getParent(y)] = parent[getParent(x)];			

			return size[getParent(x)];
		} else if (size[getParent(x)] < size[getParent(y)]) {			
			size[getParent(y)] += size[getParent(x)];
			parent[getParent(x)] = parent[getParent(y)];					

			return size[getParent(y)];
		} else {
			size[getParent(x)] += size[getParent(y)];
			parent[getParent(y)] = parent[getParent(x)];			

			return size[getParent(x)];			
		}
	}
}

class Trie {
	public Node root;

	public Trie() {
		this.root = new Node();
	}

	public void put(String s, int val) {
		put(this.root, s, s.length(), 0, val);
	}

	private void put(Node t, String s, int len, int pos, int val) {
		if (pos == len) {
			t.val = val;
		} else {
			char c = s.charAt(pos);
			if (!t.m.containsKey(c)) {
				t.m.put(c, new Node(c));
			}

			put(t.m.get(c), s, len, pos+1, val);
		}
	}

	public Integer get(String s) {
		return get(this.root, s, s.length(), 0);
	}

	private Integer get(Node t, String s, int len, int pos) {
		if (pos == len) {
			return t.val;
		} else {
			char c = s.charAt(pos);
			if (!t.m.containsKey(c)) {
				return null;
			} else {
				return get(t.m.get(c), s, len, pos+1);
			}
		}
	}

	public boolean containsKey(String s) {
		return get(s) != null;
	}
}

class Node {
	public Character c;
	public HashMap<Character,Node> m;
	public Integer val;

	public Node(Character c) {
		this.c = c;
		this.m = new HashMap<Character,Node>(52); // 26 uppercase and 26 lowercase
	}

	public Node() {
		this.m = new HashMap<Character,Node>(52); // 26 uppercase and 26 lowercase
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
