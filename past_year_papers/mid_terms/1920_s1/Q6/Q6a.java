import java.util.*;

public class Q6a {
	public static void main(String[] args) {
	/* Test Case 1 */
		// int[] a = {1,3,7,1,4};
		// int h = 190;

	/* Test Case 2 */
		int[] a = {1,3,1,4,7};
		int h = 190;

		int currIndex = 0;
		int timeRemaining = h;
		int fullBottles = 0;

		Queue<Integer> q = new LinkedList<Integer>();

		while (currIndex <= a.length || !q.isEmpty()) {
			int currBottle;
			if (currIndex < a.length) {				
				currBottle = a[currIndex];
				currIndex++;
			} else {
				currBottle = q.poll();
			}			

			// System.out.format("Current bottle: %d\n Time remaining: %d\n", currBottle, timeRemaining);

			if (currBottle <= 3) {	
				int timeConsumed = currBottle * 10;				

				if (timeRemaining - timeConsumed <= 0) {
					break;
				} else {
					timeRemaining = timeRemaining - timeConsumed - 10;
					fullBottles++;
					// System.out.println("full!");
				}
			} else {
				int timeConsumed = Math.min(currBottle, 3) * 10;

				if (timeRemaining - timeConsumed <= 0) {
					break;
				} else {					
					timeRemaining = timeRemaining - timeConsumed - 10;

					if (currBottle - 3 <= 0) {
						fullBottles++;
						// System.out.println("full!");
					} else {
						q.offer(currBottle-3);	
					}					
				}
			}			
		}

		System.out.println(fullBottles);
	}
}