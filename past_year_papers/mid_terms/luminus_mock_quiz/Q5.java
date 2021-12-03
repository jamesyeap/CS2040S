import java.util.*;

public class Q5 {

    static int reversedBinarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
  
            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;
  
            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] < x)
                return reversedBinarySearch(arr, l, mid - 1, x);
  
            // Else the element can only be present
            // in right subarray
            return reversedBinarySearch(arr, mid + 1, r, x);
        }
  
        // We reach here when element is not present
        // in array
        return -1;
    }

	public static void main(String[] args) {
		// test1
		int test1[] = {5,4,3,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

		int a[] = test1;

		int left=0, right=a.length;
		int middle=(left+right)/2;

		while (a[middle] == 0) {
			right = middle-1;
			middle=(left+right)/2;
		}

		while (a[middle-1] == 0 && a[middle+1] != 0) {
			if (a[middle-1] == 0) {
				left = middle;
				middle=(left+right)/2;
			} else {
				right = middle;
				middle=(left+right)/2;
			}			
		}

		int indexOfFirstZero = middle+1;
		// System.out.println(reversedBinarySearch(a, 0, indexOfFirstZero, 1));

		System.out.println(reversedBinarySearch(a, 0, a.length-1, 5));

		// for (int i=left; i<=right; i++) {
		// 	System.out.print(a[i]);
		// }
	}
}