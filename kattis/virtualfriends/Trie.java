import java.util.*;

public class Trie {
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