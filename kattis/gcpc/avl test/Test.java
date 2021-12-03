import java.util.*;
import myavl.*;

public class Test {
	public static void main(String[] args) {
		AVL a = new AVL();

		for (int i=1; i<=9; i++) {
			a.put(i, -1);
		}		

		a.inorderPrint();

		Node t = a.root;

		t = t.left;
		System.out.println(t);

		t = a.findSuccessor(t);
		System.out.println(t);

		t = a.get(9);
		System.out.println(t);

		a.remove(5);

		System.out.println();
		a.inorderPrint();


	}
}