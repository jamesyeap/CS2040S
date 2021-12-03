package myavl;

public class Node {
	public int key;
	public int val;

	public Node parent;
	public Node left;
	public  Node right;

	public int height;
	public int size; 

	public Node(int key, int val) {
		this.key = key;
		this.val = val;

		this.height = 0;
		this.size = 1;

		// System.out.println(this);
	}

	@Override
	public String toString() {
		return String.format("<%d, %d>", this.key, this.val);
	}
}