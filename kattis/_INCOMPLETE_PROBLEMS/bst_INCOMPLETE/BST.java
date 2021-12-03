import java.util.*;
import java.io.*;

public class BST {
	public static void main(String[] args) {
		// Scanner sc = new Scanner(System.in);
		Kattio io = new Kattio(System.in, System.out);

		int n = io.getInt();

		BST t = new BST();
		// TreeMap<Integer,Node> m = new TreeMap<>();
		Node largestSeen = null;

		for (int i=0; i<n; i++) {
			int x = io.getInt();

			if (largestSeen != null && x > largestSeen.val) {								
				largestSeen = t.insert(largestSeen, x, largestSeen.depth);				
			} else {
				if (largestSeen == null) {
					largestSeen = t.insert(x);
				} else {
					t.insert(x);
				}							
			}

			io.println(t.c);
		}

		io.close();
	}
}

class Node {
	Integer val;
	Node left;
	Node right;

	int depth;

	public Node() {
		//
	}

	public Node(int val) {
		this.val = val;
	}
}

class BST {
	public Node root;
	public static int c;

	public BST() {		
		this.c = 0;
	}

	public Node insert(int i) {
		this.root = insert(this.root, i, 0);

		return this.root;
	}

	public Node insert(Node t, int i, int depth) {
		if (t == null) {	
			Node n = new Node(i);
			n.depth = depth;					
			c += depth;			

			return n;
		} else {
			if (t.val > i) { // go left
				t.left = insert(t.left, i, depth+1);
			} else { 		// go right
				t.right = insert(t.right, i, depth+1);
			}

			return t;
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
