import java.util.*;
import java.io.*;

public class GCPC {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int n = io.getInt();
		int m = io.getInt();

		AVL t = new AVL();		
		Team[] teams = new Team[n+1];		

		for (int i=1; i<=n; i++) {		
			teams[i] = new Team(i);			
			t.add(teams[i]);
		}

		for (int i=0; i<m; i++) {
			int teamId = io.getInt();
			int penalty = io.getInt();					

			t.remove(teams[teamId]);

			teams[teamId].nProblems++;
			teams[teamId].penalty+=penalty;

			t.add(teams[teamId]);			
			
			io.println(n - t.rank(teams[1]) + 1);
		}		

		io.close();
	}
}	

class Team implements Comparable<Team> {
	public int teamId;
	public int nProblems;
	public int penalty;

	public Team(int teamId) {
		this.teamId = teamId;		
		this.nProblems = 0;
		this.penalty = 0;
	}

	@Override
	public int compareTo(Team s) {
		if (this.nProblems > s.nProblems) {
			return 1;
		} else if (this.nProblems < s.nProblems) {
			return -1;
		} else {
			if (this.penalty > s.penalty) {
				return -1;
			} else if (this.penalty < s.penalty) {
				return 1;
			} else {
				return s.teamId - this.teamId;
			}
		}
	}

	@Override
	public String toString() {
		return String.format("%d %d %d", teamId, nProblems, penalty);
	}
}

/* an implementation of an AVL tree */
class AVL {
	public Node root;

	public AVL() {
		
	}
	
	/* inserts a new node into the tree */
	public void add(Team key) {
		Node n = new Node(key);

		this.root = add(this.root, n);			
	}

	public Node add(Node t, Node n) {
		if (t == null) { return n; }

		int comparison = n.key.compareTo(t.key);

		/* INSERTION STEP */
		if (comparison > 0) { 		// if the new node is greater than this node, insert it in the right-subtree
			t.right = add(t.right, n);			
			t.right.parent = t;										// make sure the child knows who the parent is			
		} else { // if the new node is smaller than this node, insert it in the left-subtree
			t.left = add(t.left, n);
			t.left.parent = t;										// make sure the child knows who the parent is			
		}

		updateHeight(t); // update the node's height
		updateSize(t); 	 // 	update the node's size

		/* REBALANCING STEP */
		//		make sure that, after inserting, the tree remains kinda balanced
		t = balance(t);		

		return t;
	}

	/* removes a node containing the given key from the tree */
	public void remove(Team key) {
		this.root = remove(this.root, key);
	}

	public Node remove(Node t, Team key) {
		if (t == null) { return null; }

		int comparison = t.key.compareTo(key);

		if (comparison > 0) {					// search left subtree for the node
			t.left = remove(t.left, key);
		} else if (comparison < 0) {			// search right subtree for the node
			t.right = remove(t.right, key);
		} else {							// found the node					
			if (t.left == null && t.right == null) {			// CASE 1: node is a leaf-vertex
				return null;	// can just return null
			} else if (t.left != null && t.right == null) {	// CASE 2: node has a left sub-tree
				t.left.parent = t.parent; // connect the root of the left sub-tree to the parent of t
				t = t.left;
			} else if (t.left == null && t.right != null) {	// CASE 3: node has a right sub-tree
				t.right.parent = t.parent; // connect the root of the right sub-tree to the parent of t
				t = t.right;
			} else {												// CASE 4: node has both left and right sub-trees
				Node x = findSuccessor(t);	// search the right-subtree of t for its successor
				t.key = x.key;	// replace the contents of t with the contents of its successor (do it inplace)				
				t.right = remove(t.right, x.key); // then, delete the successor (if not will have 2 copies of its successor in the tree)
			}			
		}

		updateHeight(t);
		updateSize(t);

		return balance(t); // at every level, check if the subtree is balanced, and make the modifications if not
	}

	/* modifies the tree such that the height-difference between the left and right subtrees are <= 1 */
	// balance
	public Node balance(Node t) {
		if (t == null) { return null; }

		// check if, at this node, its left and right subtree are kinda balanced (height difference no more than 1)
		//		if not, need to modify
		if (balanceFactor(t) == 2) { 										// if left-subtree is too heavy
			if (balanceFactor(t.left) == 0 || balanceFactor(t.left) == 1) { // 		and if this is caused by the left-subtree of the left-subtree being too heavy
				t = rightRotate(t);
			} else {														//		or if this is caused by the right-subtree of the left-subtree being too heavy
				t.left = leftRotate(t.left);
				t = rightRotate(t);
			}
		}

		if (balanceFactor(t) == -2) { 										// if right-subtree is too heavy			
			if (balanceFactor(t.right) == 0 || balanceFactor(t.right) == -1) { // 		and if this is caused by the right-subtree of the right-subtree being too heavy				
				t = leftRotate(t);
			} else {														//		or if this is caused by the left-subtree of the right-subtree being too heavy				
				t.right = rightRotate(t.right);
				t = leftRotate(t);
			}			
		}

		// after modifying this node's left and right subtrees, return it
		return t;
	}

	public int balanceFactor(Node t) {						
		return getHeight(t.left) - getHeight(t.right);
	}

	/* modifies the given node such that its right-child occupies its position, and correct ordering is maintained */	
	public Node leftRotate(Node t) {
		Node q = t.right;

		q.parent = t.parent; // q's parent is no longer t

		t.right = q.left;						  // t needs to preserve the left-child of q, if any
		q.left = t; 							  // t is now under q

		t.parent = q;							  // make sure t knows this

		updateHeight(t);
		updateHeight(q);

		updateSize(t); // order matters i think: size of t must be updated first before q
		updateSize(q);

		return q;
	}

	/* modifies the given node such that its left-child occupies its position, and correct ordering is maintained */
	public Node rightRotate(Node t) {
		Node q = t.left;

		q.parent = t.parent; // same as leftRotate

		t.left = q.right; 						   // t needs to preserve the right-child of q, if any
		q.right = t;							   // t is now under q

		t.parent = q;							   // make sure t knows this

		updateHeight(t);
		updateHeight(q);

		updateSize(t); // order matters i think: size of t must be updated first before q
		updateSize(q);

		return q;
	}

	/* updates the height of the given-node */
	public void updateHeight(Node t) {
		if (t == null) { return; }

		int leftHeight = t.left != null ? t.left.height : -1;
		int rightHeight = t.right != null ? t.right.height : -1;

		t.height = Math.max(leftHeight, rightHeight) + 1;
	}

	/* returns height of node, or -1 if node is null */
	public int getHeight(Node t) {
		if (t == null) { return -1; }

		return t.height;
	}

	/* updates the size of the given-node */
	public void updateSize(Node t) {
		if (t == null) { return; }

		int leftSize = t.left != null ? t.left.size : 0;
		int rightSize = t.right != null ? t.right.size : 0;

		t.size = leftSize + rightSize + 1;
	}

	/* returns size of node, or 0 if node is null */
	public int getSize(Node t) {
		if (t == null) { return 0; }

		return t.size;
	}

	/* returns the next-greater node relative to the given-node, or null if doesn't exist */	
	public Node findSuccessor(Node t) {		
		if (t.right != null ) {			// CASE 1: node has a right subtree
			return findMin(t.right);	// 		the next greater node must then be somewhere inside it -> note that we want the NEXT greater one, so its like just "1 step" bigger 
		} else {						// CASE 2: node does not have a right subtree			
			Node curr = t;
			Node par = t.parent;

			while (par != null && curr == par.right) {
				curr = par;
				par = curr.parent;
			}

			if (par == null) {
				return null;
			} else {
				return par;
			}
		}
	}

	/* returns the greatest node in the tree, or null if doesn't exist */
	public Node findMax(Node t) {
		if (t.right == null) { return t; }

		return findMax(t.right);
	}

	/* returns the smallest node in the tree */
	public Node findMin(Node t) {
		if (t.left == null) { return t; }

		return findMin(t.left);
	}

	/* prints the keys of all the nodes in the tree INORDER */
	public void inorderPrint() {
		inorderPrint(this.root);
	}	

	public void inorderPrint(Node t) {
		if (t == null) { return; }

		inorderPrint(t.left);
		System.out.println(t);
		inorderPrint(t.right);
	}

	/* gets the rank of a given key */
	public int rank(Team s) {
		// starting from the root...
		return rank(this.root, s);
	}

	public int rank(Node t, Team s) {
		// ...traverse down the tree, looking for the given Team

		int comparison = s.compareTo(t.key);
		if (comparison < 0) { 			// if the given Team is smaller than the current node, 
			return rank(t.left, s);		// 		search in the left-subtree
		} else if (comparison > 0) {						// if the given Team is bigger than the current node, 
			return rank(t.right, s) + getSize(t.left) + 1;  // 		means that it is somewhere in the right subtree
															// 		also, it is bigger than the current node, and all the nodes in the left subtree of this node			
		} else {  // if the node contains the given Team 
			return getSize(t.left) + 1; // return the size of the node's left subtree
		}	
	}

}

/* my Node implementation */
class Node {
	public Team key;	

	public Node parent;
	public Node left;
	public  Node right;

	public int height;
	public int size; 	

	public Node(Team key) {
		this.key = key;		

		this.height = 0;
		this.size = 1;				
	}

	@Override
	public String toString() {
		return this.key.toString();
	}
	
	public int compareTo(Node x) {
		return this.key.compareTo(x.key);
	}
}

/* I/O functions provided by Kattis */
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
