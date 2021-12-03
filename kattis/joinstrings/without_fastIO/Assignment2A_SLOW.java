import java.util.*;
import java.io.*; 

public class Assignment2A_SLOW {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int nStrings = sc.nextInt();
		sc.nextLine();

		String[] strArr = new String[nStrings+1];
		ArrayList<Node> orderArr = new ArrayList<Node>(nStrings+1);
		orderArr.add(0, new Node(-1)); // dummy Node to pad the ArrayList

		for (int i=1; i<nStrings+1; i++) {
			strArr[i] = sc.nextLine();

			orderArr.add(i, new Node(i));
			// System.out.println(orderArr.get(i).toString());
		}


		int startFrom = 1; 
		String command; 
		String[] splitCommand;
		int a;
		int b;
		for (int i=0; i<nStrings-1; i++) {
			command = sc.nextLine();
			splitCommand = command.split(" ");
			a = Integer.parseInt(splitCommand[0]);
			b = Integer.parseInt(splitCommand[1]);

			orderArr.get(a).addNext(orderArr.get(b));

			if (i == nStrings-2) {
				startFrom = a;
			}
		}

		Node yeahBoi = orderArr.get(startFrom);
		while (yeahBoi != null) {
			System.out.print(strArr[yeahBoi.index]);
			
			yeahBoi = yeahBoi.next;
		}
	}
}

class Node {
	public int index; 
	public Node next;
	public Node tail;

	public Node(int index) {
		this.index = index;
		this.next = null;
		this.tail = null;
	}

	public void addNext(Node nextNode) {
		if (this.isLastNode()) {
			this.next = nextNode;
			this.tail = nextNode;
		} else {
			this.tail.addNext(nextNode);
			this.tail = nextNode;
		}
	}

	// public Node getLastNode() {
	// 	if (this.isLastNode()) {
	// 		return this;
	// 	} else {
	// 		return this.next.getLastNode();
	// 	}
	// }

	public boolean isLastNode() {
		return this.next == null;
	}

	@Override
	public String toString() {
		return String.format("index: %d, nextNode: %s, nextNode: %s", this.index, this.next, this.tail);
	}
}