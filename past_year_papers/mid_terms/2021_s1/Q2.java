import java.util.*;

public class Q2 {
	public static void main(String[] args) {
		/* TEST-CASE */
		Integer[] firstDS = {1,3,5};
		Integer[] secondDS = {2,4};

		/* Option A: both are Queues */
		Queue<Integer> x = new LinkedList<Integer>(Arrays.asList(firstDS));
		Queue<Integer> y = new LinkedList<Integer>(Arrays.asList(secondDS));

		while (!y.isEmpty()) {
			x.offer(y.poll());
		}
		
		Integer temp;
		do {
			temp = x.poll();
			x.offer(temp);
		} while (x.peek() < temp);

		System.out.println(x);


	}
}