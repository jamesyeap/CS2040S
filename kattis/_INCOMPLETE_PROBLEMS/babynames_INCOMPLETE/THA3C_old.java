import java.util.*;
import java.io.*;

public class THA3C {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Trie names = new Trie();

		String command = sc.nextLine();
		char start = command.charAt(0);

		while (start != '0') {
			if (start == '1') {
				String name = command.substring(2, command.length()-2);
				int gender = Character.getNumericValue(command.charAt(command.length()-1));				

				names.insert(name, gender);			
			} else if (start == '2') {
				String name = command.substring(2, command.length());

				names.remove(name);				
			} else {
				int gender = Character.getNumericValue(command.charAt(command.length()-1));
				String[] limits = command.substring(2, command.length()-2).split(" ");

				// System.out.println(command.substring(2, command.length()-2));

				// System.out.format("%s %s %d\n", limits[0], limits[1], gender);

				// System.out.println(names.count(limits[0], limits[1], gender));


			}

			command = sc.nextLine();
			start = command.charAt(0);
		}

		names.print();

		names.insert("ABC", 1);
		names.insert("ACC", 1);
		names.insert("ABXCC",1);
		names.insert("CDF", 1);

		System.out.println(names.count("A", "CC", 1));
	}
}

class Node {
	public Character val;
	public Node parent;
	public HashMap<Character,Node> next;
	public Integer gender; // 0 for both genders, 1 for male, 2 for female
	public boolean isLast; // true if this node is the last character of a word

	public Node() {
		this.next = new HashMap<Character, Node>(26); // 26 possible alphabets		
		this.isLast = true;
	};

	public Node(char val) {
		this.val = val;

		this.next = new HashMap<Character, Node>(26); // 26 possible alphabets		
		this.isLast = true;		
	}

	@Override
	public String toString() {
		if (this.val == null) {
			return "";
		}

		return Character.toString(this.val);
	}
}

class Trie {
	public Node root; 

	public Trie() {
		this.root = new Node();
	}

	public void insert(String name, int gender) {
		insert(this.root, name, name.length(), 0, gender);		
	}

	private boolean insert(Node t, String name, int len, int pos, int gender) {
		if (pos == len) { 			// if this is the last character of the name has been stored
			if (t.gender == null) {			// if this is the first time the name has been inserted
				t.gender = gender;
			} else {
				if (t.gender != gender) { 	// if this name was added but for a different gender
					t.gender = 0;			// 		now the name can be used for both genders
				}
			}

			return false;	
		} else {

			char c = name.charAt(pos);
			if (!t.next.containsKey(c)) {
				Node n = new Node(c);
				n.parent = t;

				t.next.put(c, n);			
			}	
					
			t.isLast = insert(t.next.get(c), name, len, pos+1, gender);			

			return t.isLast;
		}
	}

	public void remove(String name) {
		remove(this.root, name, name.length(), 0);
	}

	private boolean remove(Node t, String name, int len, int pos) {
		if (pos == len) {
			t.gender = null; // remove this value

			return t.isLast;
		} else {
			char c = name.charAt(pos);
			boolean nextWasLast = remove(t.next.get(c), name, len, pos+1);			

			if (nextWasLast == true) {
				t.next.remove(c);

				if (t.next.size() == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	public int count(String a, String b, int gender) {
		return count(this.root, a, b, gender);
	}

	private int count(Node t, String a, String b, int gender) {
		// go to subtree where the root is the last character of string a
		//		if this subtree doesn't exist, return 0;

		// in this subtree, do DFS to the leaf vertices:
		//		from the leaf vertices:
		//			if the value exists and matches the gender specified:
		//				traverse upwards and check, from back to front of string b, if each vertex matches
		//					if bPos == 0, means all matched: return 1
		//					if don't match: return 0;

		Node pTree = findPrefixTree(a);

		if (pTree == null) {
			return 0;
		}

		int totalMatches = 0;
		for (int i='A'; i<='Z'; i++) {
			if (pTree.next.containsKey((char) i)) {
				totalMatches += nameExists(pTree.next.get((char) i), b, gender);
			}
		}

		return totalMatches;
	}

	private Node findPrefixTree(String s) {
		return findPrefixTree(this.root, s, s.length(), 0);
	}

	private Node findPrefixTree(Node t, String s, int len, int pos) {
		if (t == null) { return null; }

		if (pos == len-1) {
			return t;
		}

		char c = s.charAt(pos);

		return findPrefixTree(t.next.get(c), s, len, pos+1);
	}

	private int nameExists(Node t, String s, int gender) {		
		if (t.isLast == true) {
			if (t.gender != null && (t.gender == 0 || t.gender == gender)) {
				return matchPostfix(t, s, s.length()-1);
			} else {
				return 0;
			}
		} else {
			int matches = 0;

			for (int i='A'; i<='Z'; i++) {
				if (t.next.containsKey((char) i)) {
					matches += nameExists(t.next.get((char) i), s, gender); 
				}
			}

			return matches;
		}
	}

	private int matchPostfix(Node t, String s, int pos) {
		if (pos <= -1) { return 1; }

		if (t.val == null || t.val != s.charAt(pos)) {
			return 0;
		}

		return matchPostfix(t.parent, s, pos-1);
	}

	public void print() {
		print(this.root, "");
	}

	private void print(Node t, String s) {
		if (t.isLast == true) {
			System.out.println(s);
		} else {
			for (int i='A'; i<='Z'; i++) {
				if (t.next.containsKey((char) i)) {
					print(t.next.get((char) i), s + Character.toString(i));
				}
			}
		}
	}
	
}
