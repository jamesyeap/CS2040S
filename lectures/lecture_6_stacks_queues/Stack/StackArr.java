import java.util.*;

class StackArr implements StackADT {
	private int[] arr;
	private int capacity;
	private int top;
	private static final int INITIAL_CAPACITY = 1; 
	 
	public StackArr() {
		arr = new int[INITIAL_CAPACITY];
		capacity = INITIAL_CAPACITY;
		top = -1;
	}

	// check whether stack is empty
	public boolean empty() {
		return (top < 0);
	}

	// retrieve topmost item on stack
	public Integer peek() {
		return arr[top];
	}

	// remove and return topmost item on stack
	public Integer pop() {
		int result = arr[top];
		top--;

		return result;
	}

	// insert item onto stack
	public void push(Integer item) {
		// if the array used is full, then need to double the size of the array
		if (top+1 >= capacity) {
			enlargeArr();
		}

		top++;
		arr[top] = item;
	}

	private void enlargeArr() {
		// just double the size of the array
		int newCapacity = capacity * 2; 
		int[] newArr = new int[newCapacity];

		for (int i=0; i<=top; i++) {
			newArr[i] = arr[i];
		}

		arr = newArr;
		capacity = newCapacity;
	}
}