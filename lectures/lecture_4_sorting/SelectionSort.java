import java.util.Arrays;
/*
	BIG IDEA

	Starting from the first element, find the smallest element on the right-hand side (unsorted side),
		then, swap the first element with the smallest element found.
	
	Repeat the process for the second element, and so on, until the second-last element.
*/

/*
	TIME-COMPLEXITY ANALYSIS

	Outer Loop: 
		Starting from the left, swap the first element of the array
			with the smallest element to the right-side (unsorted-side)

		Number of Operations = [n-1]

	Inner Loop:
		Iterating through all the elements on the right-hand side
			of the array to find the smallest element.

		So, it goes something like:
			1st iteration: [n-1] passes
			2nd iteration: [n-2] passes
			3rd iteration: [n-3] passes
			(...)
			[n-1]th iteration: 1 pass

	
	Hence, the total Time-Complexity for this algorithm is: 
		Number of Operations = [n-1] + [n-2] + [n-3] + ... 1
*/

/*
	NOTE

	Another variation is to find the largest item in the array, then
		swap it with the item at the end of the array.

	Repeat the process, excluding the largest items (at the end of the array)
*/

public class SelectionSort {
	public static void swap(int[] a, int j, int k) {
		int temp = a[j];
		a[j] = a[k];
		a[k] = temp; 
	}

	public static void selectionSort(int[] a) {
		// iterate from the first element to the second-last element
		for (int i=0; i<a.length-1; i++) {
			// find the smallest element to the right of the current element
			int indexOfSmallestFound = i;
			for (int j=i+1; j<a.length; j++) {
				if (a[indexOfSmallestFound] > a[j]) {
					indexOfSmallestFound = j;
				}
			}

			// swap the current element with the smallest element found to its right
			swap(a, i, indexOfSmallestFound);
		}
	}

	public static void main(String[] args) {
		int[] test = {6, 5, 2, 3, 4};
		selectionSort(test);

		System.out.println(Arrays.toString(test));
	}
}