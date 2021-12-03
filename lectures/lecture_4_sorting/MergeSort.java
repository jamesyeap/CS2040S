import java.util.Arrays;

/*
	BIG IDEA

	Merge-Sort has 2 PHASES -> [DIVIDE-PHASE] and [CONQUER-PHASE]
		aka "Divide and Conquer" strategy
			- "it's a common strategy in Computer Science"

	[DIVIDE-PHASE]
	First, break down the array into sub-arrays repeatedly 
		until you only have a single element in each sub-array.

	Observe that the BASE-CASE is when the subarray only has one element, which
		can be seen when i=j (see the code)

	Then, observe that a sub-array with only one single element is sorted.
		(because duh)

	[CONQUER-PHASE]
	Then, merge 2 sorted sub-arrays into a single sorted array,
		and repeat for all sub-arrays until there are no more sub-arrays to merge.

	
*/

/**
 * TIME-COMPLEXITY ANALYSIS
 * 
 * [DIVIDE-PHASE]
 * Keep dividing the unsorted array in two.
 * 
 * So, it goes something like this:
 * 	1st iteration
 * 		- divide the array into 2 sub-arrays
 * 
 * 	2nd iteration
 * 		
 * TODO 
 * 
 * [CONQUER-PHASE]
 * at every merge-step, you need to:
 * 		[STEP 1]
 * 		compare the first-elements of each of the two sorted sub-arrays
 * 		and add the smaller element to the temporary sub-array
 * 			Number of Operations = n/2
 * 
 * 		[STEP 2]
 * 		once one of the two sorted sub-arrays is exhausted,
 * 		you need to copy over the rest of the elements from the 
 * 		remaining sub-array over to the temporary array
 * 			Number of Operations = n/2 
 * 		
 * 		
 * so, the total Number of Operations is:
 *	Number of Operations = n/2 + n/2 = n
 * 
 * Quote: "The time-complexity is inear in the combined size of the sub-arrays"
 * 
 * [RECURRENCE-RELATION]
 * - TODO (refer to 35 min mark)
 */

public class MergeSort {
	/****** [DIVIDE-PHASE] ******/

	//	sorts an array from the jth position to kth (inclusive) position
	public static void mergeSort(int[] a, int j, int k) {
		//	[BASE-CASE]
		//	if the array only has a single-element, it's sorted, so
		//		nothing needs to be done
		if (j < k) { // this line checks for [BASE-CASE]
			//	find the mid-point of the array
			int mid = (j+k)/2;

			//	sort the left-side of the array 
			//		(note: if the array length is odd, then the left-side of the array will hold one more element)
			mergeSort(a, j, mid);
			//	sort the right-side of the array
			mergeSort(a, mid+1, k);

			//	merge the left-hand and right-hand side of the array
			merge(a, j, mid, k); 
		}
	}

	/****** [CONQUER-PHASE] ******/

	//	merges 2 sorted sub-arrays into a single-sorted array 
	//		first sub-array: ith to [mid]th position (inclusive) of the array
	//		second sub-array: [mid+1]th to kth position (inclusive) of the array
	public static void merge(int[] a, int i, int mid, int k) {
		// initialize a temporary array to hold the sorted elements;
		int[] temp = new int[k-i+1];
		int tempIndex = 0;

		//	set the pointers for the first and second sub-arrays to point to their
		//		first elements
		int m=i, n=mid+1;

		//	while both of the sub-arrays have elements in them
		while (m<=mid && n<=k) {
			//	take the smallest element between the two sub-arrays, and
			//		place it in the temporary array
			if (a[m] < a[n]) {
				temp[tempIndex++] = a[m++];
			} else {
				temp[tempIndex++] = a[n++];
			}
		}

		//	at the end, it is possible that one of the two sub-arrays still have elements in them
		//		if this is the case, then just copy over the remaining elements from this 
		//		sub-array wholesale to the temporary sorted temporary array 
		//
		//	note:
		//	only one of these cases will run
		//		(why?)
		//		because of the while-loop condition above: code: [while (m<=mid && n<=k)]
		//		which will exit only if one of the subarrays is completely-exhausted

		//	CASE 1
		//		if the left subarray is not completely-exhausted
		while (m<=mid) {  
			// copy over 
			temp[tempIndex++] = a[m++];
		}

		// CASE 2
		//		if the right subarray is not completely-exhausted
		while (n<=k) { // if the right subarray is not completely-exhausted
			temp[tempIndex++] = a[n++];
		}

		// overwrite the elements in the original array
		//	with the sorted elements in the temporary array
		for (int q=0; q<temp.length; q++) {
			a[i+q] = temp[q]; 
		}
	}

	/***** NOT REALLY RELATED *****/
	public static void mergeSort(int[] a) {
		mergeSort(a, 0, a.length-1);
	}

	public static void main(String[] args) {
		int[] testCase1 = {5, 4, 1, 2, 3, 50, 20, 1}; 
		mergeSort(testCase1);
		System.out.println(Arrays.toString(testCase1));
	}
}