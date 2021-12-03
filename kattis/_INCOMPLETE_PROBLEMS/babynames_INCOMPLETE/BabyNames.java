import java.util.*;
import java.io.*;

public class BabyNames {
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
	public int size;

	public Node() {
		this.next = new HashMap<Character, Node>(26); // 26 possible alphabets		
		this.isLast = true;
		this.size = 1;
	};

	public Node(char val) {
		this.val = val;

		this.next = new HashMap<Character, Node>(26); // 26 possible alphabets		
		this.isLast = true;
		this.size = 1;	
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
		this.root.size = insert(this.root, name, name.length(), 0, gender);		
	}

	private int insert(Node t, String name, int len, int pos, int gender) {
		if (pos == len) { 			// if this is the last character of the name has been stored
			if (t.gender == null) {			// if this is the first time the name has been inserted
				t.gender = gender;
			} else {
				if (t.gender != gender) { 	// if this name was added but for a different gender
					t.gender = 0;			// 		now the name can be used for both genders
				}
			}			
		} else {

			char c = name.charAt(pos);
			if (!t.next.containsKey(c)) {
				Node n = new Node(c);
				n.parent = t;

				t.next.put(c, n);			
			}	
					
			insert(t.next.get(c), name, len, pos+1, gender);
			t.isLast = false;

			//
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
