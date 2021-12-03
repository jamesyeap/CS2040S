import java.util.Arrays;
/**	
 * BIG IDEA
 * 
 * 1. Pick the first element of the array to be the Pivot Element 
 * 		- has a pointer "i" that points to it
 * 2. Divide the region of the array to the right of the first element into 3 sub-regions:
 * 		S1: this region will only hold elements LESSER-THAN the Pivot Element
 * 			- initially empty
 * 			- has a pointer "m" that points to the last-element of this region
 * 		S2: this region will only hold elements EQUAL-TO or GREATER-THAN the Pivot Element
 * 			- initially empty
 * 		S3: this region holds elements that has not been compared to the Pivot Element yet
 * 			- has a pointer "k" that points to the first-element of this region
 * 				- points to the current-element being compared to the Pivot Element
 * 			- has a pointer "j" that points to the last-element of this region
 * 				- needed as the entire-array may not be combed through (true for iterations after the first iteration)
 * 
 * 3. Iterate through the elements in S3, comparing each element to the Pivot Element:
 * 		[CASE 1] 
 * 		When the current-element is greater than or equal to the Pivot Element,
 * 		place the current-element inside S2 by:
 * 			1. incrementing k
 * 
 * 		[CASE 2] 
 * 		When the current-element is lesser than the Pivot Element,
 * 		place the current-element inside S1 by:
 * 			1. increment m
 * 			2. swap x with y
 * 			3. increment k
 * 
 * 4. Once the last element in S3 has been sorted, swap the last-element in the S1 region
 * 		with the Pivot Element
 * 
 * 5. Using the current index of the Pivot Element within the array,
 * 		5a. Perform Quick-Sort again (recursively) on the elements to the left-side, and,
 * 		5b. Perform Quick-Sort again (recursively) on the elements to the right-side
 * 
 * [BASE-CASE]
 * When there is only one element left (same as Merge-Sort)
 * 
 * Notes:
 * 	1. it is possible to use an "Insertion-Sort" like method to shift the elements in S1, but
 * 		this method will make Quick-Sort very slow.
 * 	2. can pick other elements other than the first element to be the pivot
 * 		- see: Randomized Pivoting 
 * 		- can help to reduce the chance of occurring Worst-Case Scenario
 * 			and bring the performance close to that of Merge-Sort
 */

/**
 * TIME-COMPLEXITY ANALYSIS
 * 
 * WORST-CASE SCENARIOS: O(n^2)
 * 		occurs when: the array is already sorted (refer to slide 45 for the illustration)
 * 			- equation is an Arithmetic-Progression (TODO)
 * 
 * 		also occurs when: the array is reverse-sorted (the tree-diagram will be the mirror image of the illutration in slide 45)
 * 
 * BEST-CASE SCENARIO: O(n logn)
 * 		TODO
 * 
 * 
 * COMMENTS BY DR CHONG:
 * algorithmically-speak, Merge-Sort is better. 
 * but, you can change Quick-Sort so that it's very unlikely you run 
 * into the Worst-Case Scenario by using Randomized Pivoting.
 * 
 * Randomized Pivoting 
 * 	has an average Time-Complexity of O(n logn)
 * 
 */

public class QuickSort {
    // Wrapper Function
    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length-1);
    }

	public static void quickSort(int[] a, int i, int j) {
		// PARTITION and SORT the array only if its length is more than 1 (BASE-CASE: array only has one-element)
		if (i < j) { // this line does the check for the BASE-CASE
			/****** PARTITION-STEP ("the meat of this algorithm") *****/
			int pivotIndex = partition(a, i, j);

			/***** SORT-STEP *****/
			// recursively Quick-Sort the left and right sides of the Pivot Element
			quickSort(a, i, pivotIndex);
			quickSort(a, pivotIndex+1, j);
		}
	}

	public static int partition(int[] a, int i, int j) {
		int pivotElement = a[i];
        int m=i; // points to the last-element of S1
        int k=i+1; // points to the first-element of S3

		while (k <= j) {
			if (pivotElement <= a[k]) { 
            // [CASE 1]: if current-element is GREATER THAN or EQUAL TO the Pivot Element,
            //      place it inside region S2
                // Step 1: go on to the next element
                k++;
			} else {
			// [CASE 2]: if current-element is LESS THAN the Pivot Element,
			//		place it inside region S1
                //  Step 1: increase the S1 region by one element
                //      note: at this point, S1 erroneously contains an element from the S2 region
                m++; 
                // Step 2: swap the last-element in the S1 region (the erroneous-element) 
                //          with the current-element
                swap(a, m, k);
                // Step 3: go on to the next-element
                k++;
			}
		}

        //  [Final Step] swap the Pivot Element with the last-element in S1 
        //      note: the order will be correct after this, since all the elements 
        //             in S1 are smaller than the Pivot Element
        swap(a, i, m);
        return m;
	}

    // Swaps the positions of two elements in an array
    public static void swap(int[] a, int i, int j) {
        int tempElem = a[i];
        a[i] = a[j];
        a[j] = tempElem;
    }

	public static void main(String[] args) {
		int[] testCase1 = {6, 5, 2, 3, 4};
		quickSort(testCase1);
		System.out.println(Arrays.toString(testCase1));
	}
}