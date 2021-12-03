package myavl;

public class AVL {
	public Node root;

	public AVL() {
		this.root = null; // root is null initially
	}

	/* returns true if tree has no nodes */
	public boolean isEmpty() {
		return this.root == null;
	}

	/* returns the max number of edges from the root node to the leaf node */
	public int height() {
		return getHeight(this.root);
	}

	/* finds and returns a node with key equal to the given value, or null if doesn' exist */
	public Node get(int key) {
		return get(this.root, key);
	}

	public Node get(Node t, int key) {
		if (t == null) { return null; }

		if (t.key > key) {
			return get(t.left, key);
		} else if (t.key < key) {
			return get(t.right, key);
		} else {
			return t;
		}
	}
	
	/* inserts a new node into the tree */
	public void put(int key, int val) {
		Node n = new Node(key, val);

		this.root = put(this.root, n);			
	}

	public Node put(Node t, Node n) {
		if (t == null) { return n; }

		/* INSERTION STEP */
		if (t.key > n.key) { 		// if the new node is smaller than this node, insert it in the left-subtree
			t.left = put(t.left, n);

			t.left.parent = t;										// make sure the child knows who the parent is
			t.height = Math.max(getHeight(t.left), getHeight(t.right)) + 1; // update the parent's height
			t.size = getSize(t.left) + getSize(t.right) + 1;				// and update the parent's size
		} else if (t.key < n.key) { // if the new node is greater than this node, insert it in the right-subtree
			t.right = put(t.right, n);

			t.right.parent = t;										// make sure the child knows who the parent is
			t.height = Math.max(getHeight(t.left), getHeight(t.right)) + 1; // update the parent's height
			t.size = getSize(t.left) + getSize(t.right) + 1;				// and update the parent's size
		} else {
			return t;				// if the new node is identical to the current node, don't insert the new node (we don't allow for duplicate elements here)
									//		QUESTION: won't the height and size be incremented erroneously? if a duplicate element is found, and no new node is inserted, it seems like
									//		the height and size of the parent would still be incremented, no?
		}

		/* REBALANCING STEP */
		//		make sure that, after inserting, the tree remains kinda balanced
		t = balance(t);		

		return t;
	}

	/* removes a node containing the given key from the tree */
	public void remove(int key) {
		remove(this.root, key);
	}

	public Node remove(Node t, int key) {
		if (t == null) { return null; }

		if (t.key > key) {					// search left subtree for the node
			t.left = remove(t.left, key);

			if (t.left != null) { 			// connect the root of its left subtree to parent of t
				t.left.parent = t; 				
			}
		} else if (t.key < key) {			// search right subtree for the node
			t.right = remove(t.right, key);

			if (t.right != null) { 			// connect the root of its right subtree to parent of t
				t.right.parent = t;				
			}
		} else {							// found the node
			if (t.left == null && t.right == null) {			// CASE 1: node is a leaf-vertex
				return null;	// can just return null
			} else if (t.left != null && t.right == null) {	// CASE 2: node has a left sub-tree
				return t.left;
			} else if (t.left == null && t.right != null) {	// CASE 3: node has a right sub-tree
				return t.right;
			} else {												// CASE 4: node has both left and right sub-trees
				Node x = findSuccessor(t);	// search the right-subtree of t for its successor

				t.key = x.key;	// replace the contents of t with the contents of its successor (do it inplace)
				t.val = x.val;

				t.right = removeMin(t.right); // then, delete the successor (if not will have 2 copies of its successor in the tree)
			}
		}

		updateHeight(t);
		updateSize(t);

		t = balance(t); // at every level, check if the subtree is balanced, and make the modifications if not

		return t;
	}

	/* removes the smallest node from the tree */
	public void removeMin() {
		removeMin(this.root);
	}

	public Node removeMin(Node t) {
		if (t == null) { return null; }

		if (t.left == null) { // smallest (left-most) node in the tree is found!
			return null;
		} else {
			t.left = removeMin(t.left);
		}

		updateHeight(t);
		updateSize(t);

		t = balance(t);

		return t;
	}

	/* modifies the tree such that the height-difference between the left and right subtrees are <= 1 */
	// balance
	public void balance() {
		this.root = balance(this.root);
	}

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
		if (t == null) { return null; }

		if (t.right != null ) {			// CASE 1: node has a right subtree
			return findMin(t.right);	// 		the next greater node must then be somewhere inside it -> note that we want the NEXT greater one, so its like just "1 step" bigger 
		} else {						// CASE 2: node does not have a right subtree			
			return findSuccessor(t.parent); // go up the tree, and see if any node encountered along this path has a right-subtree
		}
	}

	/* returns the greatest node in the tree, or null if doesn't exist */
	public Node findMax(Node t) {
		if (t == null || t.right == null) { return t; }

		return findMax(t.right);
	}

	/* returns the next-smaller node relative to the given-node, or null if doesn't exist */
	public Node findPredecessor(Node t) {
		if (t == null) { return null; }

		if (t.left != null) {			// same idea as findSuccessor, but reversed
			return findMax(t.left);
		} else {
			return findPredecessor(t.parent); // same idea as findSuccessor, but reversed
		}
	}

	/* returns the smallest node in the tree */
	public Node findMin(Node t) {
		if (t == null || t.left == null) { return t; }

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


}