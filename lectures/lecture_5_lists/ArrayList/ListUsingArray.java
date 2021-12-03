import java.util.*;

class ListUsingArray implements ListInterface {
	private static int[] arr; 
	private static int numberOfItems;
	private static int capacity;
	private static final int INITIAL_CAPACITY = 1;

	public ListUsingArray() {
		arr = new int[INITIAL_CAPACITY];
		capacity = INITIAL_CAPACITY;
		numberOfItems = 0;
	}

	// Return true if list is empty; otherwise return false.
	public boolean isEmpty() { 
		if (arr.length == 0) {
			return true;
		}

		return false;
	};

	// Return number of items in the list
	public int size() { 
		return numberOfItems;
	};

  	/* access items in the list */

  	// return index of item if item is found in the list, otherwise return -1
  	public int indexOf(int item) {
  		for (int i=0; i<capacity; i++) {
  			if (arr[i] == item) {
  				return i;
  			}
  		}

  		return -1; 
  	}

  	// return true if item is in the list false otherwise
	public boolean contains(int item) {
		return (indexOf(item) != -1);
	};

	// get item at index
	public int getItemAtIndex(int index) {
		return arr[index];
	}; 

	// get first item
	public int getFirst() {
		return arr[0];
	};

	//get last item
	public int getLast() {
		return arr[numberOfItems-1];
	}
    
  	/* add items to the list */

  	//	add item at position index, 
	// 		shifting all current items from index onwards to the right by 1 
	// 		add item to the back if index == size() 
  	public void addAtIndex(int index, int item) {
  		// check if the maximum capacity has been reached
  		if (numberOfItems+1 > capacity) {
  			// if so, the array needs to be enlarged
  			enlargeArr();
  		}

  		//	starting from the back, shift all the elements 1 position backwards
  		//		backwards == to the right
  		for (int i=(numberOfItems-1); i>=index; i--) {
  			arr[i+1] = arr[i];
  		}

  		arr[index] = item;
  		numberOfItems++;


  	}

  	// add item to front of list
	public void addFront(int item) {
		addAtIndex(0, item);
	}

	// add item to back of list
	public void addBack(int item) {
		addAtIndex(size(), item);
	}

	/* remove items from the list */

	// remove item at index and return it                              
	public int removeAtIndex(int index) {
		// if the last element of the list is to be removed
		if (index == size()) {
			return removeBack();
		}

		int result = arr[index];

		//	starting from the index, shift all the elements 1 position forward
		//		forward == to the left
		for (int i=index; i<size()-1; i++) {
			arr[i] = arr[i+1];
		}
		numberOfItems--;

		return result;
	}

	// remove 1st item and return it
	public int removeFront() {
		return removeAtIndex(0);
	}

	// remove last item and return it
	public int removeBack() {
		int result = arr[size()-1];
		numberOfItems--;

		return result; 
	}

	/* print out the list from index 0 to index N-1 */
	public void print() {
		System.out.print("[ ");

		for (int i=0; i<numberOfItems; i++) {
			System.out.format("%d,", arr[i]);
		}

		System.out.println("]");
	}

	/* enlarges the size of the array by TWO times */
	private void enlargeArr() {
		int newCapacity = capacity * 2;
		int[] newArr = new int[newCapacity];

		for (int i=0; i<numberOfItems; i++) {
			newArr[i] = arr[i];
		}

		arr = newArr;
		capacity = newCapacity;
	}
}