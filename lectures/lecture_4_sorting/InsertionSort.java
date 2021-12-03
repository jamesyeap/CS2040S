import java.util.Arrays;

/*	BIG IDEA
	
	Treat the left-hand side as sorted (all elements on the right-hand side are unsorted)

	Take the first element from the right-hand side, and place it in the 
	correct position on the left-hand side.
		But, to insert this element, you need to create a space for it.
			So, you need to right-shift all the elements on the left-hand side (sorted side)
			until you either:
				CASE 1:	Hit the beginning of the array, then,
						simply insert the current element at the start of the array [DONE]
				CASE 2:	Find an element that is actually smaller than the current element
						that you wish to insert, then,
						-	stop the right-shifting process
						-	place the element inside the space created from the previous
							right-shift process

	Repeat until there are no more elements on the right-hand side.
*/

/*	TIME-COMPLEXITY ANALYSIS

	Worst-Case Scenario:
		Outer-Loop:
			Number of Operations = [n-1] times

		Inner-Loop:
			Shifting(aka Swapping) pairs of elements on the left-hand side (sorted side), to 
			create space for the nth element.

			So, it goes something like this:
				1st iteration: 1 swap
				2nd iteration: 2 swaps
				3rd iteration: 3 swaps
				(...)
				[n]th iteration: [n] swaps

	Best-Case Scenario:
		If the array is already sorted, then, 
			Number of Operations = [n-1]

		(Why?)
			Because the inner-loop (the one that does the Shifting of the sorted elements)
			is never-executed.
*/

public class InsertionSort {
	public static void InsertionSort(int[] a) {
		int currElem;

		//	iterate through all elements to the right
		for (int i=1; i<a.length; i++) {
			//	compare the current element with the last element of the 
			//		left-hand side (sorted side)
			int sortedIndex = i-1;

			//	the current element has to be saved in another variable
			//		because it may be overwritten by the inner-loop
			//		(the one that does the Shifting to create space to insert the current element)
			currElem = a[i]; 
			
			//	keep shifting elements on the left-hand side to the right to 
			//		make space for the current element to be inserted
			while (sortedIndex >= 0 && a[sortedIndex] > currElem) {
				a[sortedIndex+1] = a[sortedIndex]; 
				sortedIndex--;
			}

			//	insert the current element in the correct position on the left-hand side (sorted side)
			a[sortedIndex+1] = currElem;
		}
	}

	public static void main(String[] args) {
		int[] testCase1 = {5, 4, 1, 2, 3, 20, 2}; 
		int[] testCase2 = {1, 2, 3, 5, 4}; 

		InsertionSort(testCase1);
		InsertionSort(testCase2);

		System.out.println(Arrays.toString(testCase1));
		System.out.println(Arrays.toString(testCase2));
	}
}