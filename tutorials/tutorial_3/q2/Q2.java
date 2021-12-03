import java.util.Arrays;

public class Q2 {
	public static void main(String[] args) {
		CircularLinkedList lst = new CircularLinkedList();
		lst.addFirst(1);
		lst.add(2);
		lst.add(3);
		lst.add(4);
		lst.add(5);

		lst.print(); //	[1, 2, 3, 4, 5,]

		lst.swap(2);
		lst.print();
		//	EXPECTED: [1, 2, 4, 3, 5,] 

		lst.swap(4);
		lst.print();
		//	EXPECTED: [5, 2, 4, 3, 1,]

		lst.swap(0);
		lst.print();
		//	EXPECTED: [2, 5, 4, 3, 1,]
	}
}