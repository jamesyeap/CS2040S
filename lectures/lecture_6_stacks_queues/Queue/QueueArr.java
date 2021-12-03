class QueueArr implements QueueADT {
	private static final int INITIAL_CAPACITY = 1; 
	private Integer capacity;
	private Integer[] arr;
	private Integer front;
	private	Integer back;

	public QueueArr() {
		this.capacity = INITIAL_CAPACITY;
		arr = new Integer[capacity];
		front = 0;
		back = 0;
	}

	// return true if queue has no elements
	public boolean empty() {
		return (front == back);
	}

	// return the front of the queue 
	public Integer peek() {
		// check if the queue is empty
		if (empty()) {
			//	if so, there is nothing to return, so
			//		return null
			return null;
		}

		return arr[front];
	}

	//	add item to the back of the queue
	//		also commonly known as enqueue
	public void offer(Integer item) {
		//	to make full use of all space available in the array,
		//		treat the array as a circular array

		//	example: as the elements in the front of the queue get
		//				polled and removed, they will leave spaces, and
		//				there is no point in leaving these spaces empty, so
		//				you should use these spaces first BEFORE you enlarge
		//				the array

		//	to check if the array is indeed full (there are no spaces available in
		//		at the front of the array), do the follow check:
		if ( ((back+1) % capacity) == front ) {
			enlargeArr();
		}

		arr[back] = item;
		back = (back+1)%capacity;
	}

	//	remove and return the front of the queue
	//		also commonly known as dequeue
	public Integer poll() {
		//	check if the queue is empty
		if (empty()) {
			//	if so, there is nothing to return, so
			//		return null
			return null;
		}

		Integer item = arr[front];

		//	to make full use of all space available in the array, 
		//		treat the array as a circular array

		//	example: if front is at the last position of the array, and
		//		the rest of the queue is in the first positions of the array
		//		you should set front to the first position of the array 	

		//	to achieve this:
		front = (front+1)%capacity;

		return item;
	}


	//	replaces the current array with a new array
	//		with DOUBLE the capacity AND also
	//		retains all the elements in the current array
	private void enlargeArr() {
		int newCapacity = capacity * 2;
		Integer[] newArr = new Integer[newCapacity];

		//	keeping in mind that the current array is treated as a circular array,
		for (int i=0; i<capacity; i++) {
			newArr[i] = arr[(front+i) % capacity]; 
		}

		front = 0;
		back = capacity - 1;
		arr = newArr;
		capacity = newCapacity;
	}
}