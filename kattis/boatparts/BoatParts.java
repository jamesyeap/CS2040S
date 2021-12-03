/* use a Trie to solve */
// other possible solutions: use a Set

import java.util.*;

public class BoatParts {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String buffer = sc.nextLine();
		String split[] = buffer.split(" ");

		int p = Integer.parseInt(split[0]);
		int n = Integer.parseInt(split[1]);

		Trie t = new Trie();

		String part;
		Boolean avoided = true;
		for (int i=0; i<n; i++) {
			part = sc.nextLine();

			p -= t.insert(part);
			// System.out.format("%s, original parts left: %d\n", part, p);

			if (p == 0) {
				System.out.println(i+1);
				avoided = false;

				break;
			}
		}

		if (avoided) {
			System.out.println("paradox avoided");
		} 		

	}
}

class Trie {
	TrieNode root; 

	public Trie() {
		this.root = new TrieNode();
	}

	public int insert(String s) {
		return this.root.insert(s, 0);
	}
}

class TrieNode {
	Boolean replaced;
	HashMap<Character,TrieNode> k;

	public TrieNode() {
		this.replaced = false;
		this.k = new HashMap<>(27); // 26 alphabets and 1 '_' character
	}

	public int insert(String s, int pos) {		
		if (s.length()-1 == pos) { // if its the last-character of the string to be inserted
			return this.replace();			
		} else {
			char c = s.charAt(pos);

			if (k.containsKey(c)) {
				return k.get(c).insert(s, pos+1);
			} else {
				k.put(c, new TrieNode());

				return k.get(c).insert(s, pos+1);
			}			
		}
	}

	// returns 1 if its the first time this part was replaced; and 0 if it has already been replaced
	public int replace() {
		if (replaced) {
			return 0;
		} else {
			this.replaced = true;

			return 1;
		}
	}
}
