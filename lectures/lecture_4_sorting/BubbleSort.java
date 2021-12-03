import java.util.Arrays;

/*	BIG IDEA
	
	Starting from the first element, progressively compare pairs of elements in the array.
		If the second element in the pair is smaller than the first element, swap them.
			If not, do nothing.
		Repeat this process until the last element is reached.

	Repeat the process, ending 1 element "earlier", than the previous iteration,
		until no more element are left.
*/

/*	TIME-COMPLEXITY ANALYSIS (NORMAL BUBBLE-SORT)
	
	Outer-Loop:
		Number of Passes = [n-1]

	Inner-Loop:
		It goes something like this:
			1st iteration: [n-1] swaps 
			2nd iteration: [n-2] swaps
			3rd iteration: [n-3] swaps
			(...) 
			[n-1]th iteration: 1 swap
*/

/*	IMPROVED-VERSION OF BUBBLE-SORT

	During any single Pass, if no swap takes place,
	that means the array is already sorted!

	So, there's no need to do the remaining Passes, 
	and the function can stop. 
*/

/*	TIME-COMPLEXITY ANALYSIS (IMPROVED BUBBLE-SORT)

	Best-Case Scenario:
		If the array happens to be sorted before all Passses
		are completed.

	Worst-Case Scenario:
		Have to go through all the Passes.
			Then, the number of operations performed will be equal
			to the Normal-Bubble sort.

		When will the Worst-Case Scenario happen:
			- When the smallest element is at the end of the array
	
	Outer-Loop:
		// TODO

	Inner-Loop:
		// TODO 

*/

public class BubbleSort {
	public static void swap(int[] a, int j, int k) {
		int temp = a[j];
		a[j] = a[k];
		a[k] = temp;
	}

	public static void bubbleSort(int[] a) {
		for (int i=a.length-1; i>=0; i--) {
			for (int j=0; j<i; j++) {
				if (a[j+1] < a[j]) {
					swap(a, j, j+1);
				}
			}
		}
	}

	public static void improvedBubbleSort(int[] a) {
		// TODO 
	}

	public static void main(String[] args) {
		int[] testCase1 = {5, 4, 1, 2, 3, 50, 20, 1}; 
		bubbleSort(testCase1);
		System.out.println(Arrays.toString(testCase1));

		int[] testCase2 = {2, 1};
		bubbleSort(testCase2);
		System.out.println(Arrays.toString(testCase2));
	}
}