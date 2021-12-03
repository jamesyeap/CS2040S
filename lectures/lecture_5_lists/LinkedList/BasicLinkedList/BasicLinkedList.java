import java.util.*;

class BasicLinkedList implements ListInterface {
	private ListNode head;
	private static int numberOfItems;

	public BasicLinkedList() {
		this.head = null;
		numberOfItems = 0;
	}

	// Return true if list is empty; otherwise return false.
	public boolean isEmpty() { 
		return (head == null);
	};

	// Return number of items in the list
	public int size() { 
		return numberOfItems; 
	};

  	/* access items in the list */

  	// return index of item if item is found in the list, otherwise return -1
  	public int indexOf(int item) {
  		// start from the first node
  		ListNode curr = this.head;

 		for (int i=0; i<numberOfItems; i++) {
 			// check if the item is in the current node
 			if (curr.getItem() == item) {
 				// if so, return the current index
 				return i;
 			}

 			// set current node to the next node
 			curr = curr.getNext();
 		}

 		return -1;
  	}

  	// return true if item is in the list false otherwise
	public boolean contains(int item) {
		return (indexOf(item) != -1);
	};

	// get item at index
	public int getItemAtIndex(int index) {
		ListNode curr = this.head;

		for (int i=0; i<index; i++) {
			curr = curr.getNext();
		}

		return curr.getItem();
	}; 

	// get first item
	public int getFirst() {
		return this.head.getItem();
	};

	//get last item
	public int getLast() {
		return getItemAtIndex(size() - 1);
	}
    
  	/* add items to the list */

  	//	add item at position index, 
	// 		shifting all current items from index onwards to the right by 1 
	// 		add item to the back if index == size() 
  	public void addAtIndex(int index, int item) {
  		//	if a new node is to be added to the FRONT, 
  		//		then the HEAD of the linked list needs to be changed	
  		if (index == 0) {
  			addFront(item);
  		}

  		// access the node to the immediate LEFT of the current node at the index
  		ListNode currNode = this.head;
  		for (int i=0; i<(index-1); i++) {
  			currNode = currNode.getNext();
  		}

  		//	create a new node containing the item, and
  		//		point it to the next node of the current node
  		ListNode newNode = new ListNode(item, currNode.getNext());

  		//	point the current node to the new node
  		currNode.setNext(newNode);

  		numberOfItems++;
  	}

  	// add item to front of list
	public void addFront(int item) {
		ListNode currHead = this.head;
		ListNode newHead = new ListNode(item, this.head);

		this.head = newHead;
		numberOfItems++;
	}

	// add item to back of list
	public void addBack(int item) {
		addAtIndex(size(), item);
	}

	/* remove items from the list */

	// remove item at index and return it                              
	public int removeAtIndex(int index) {
  		//	if a node is to be removed from the FRONT, 
  		//		then the HEAD of the linked list needs to be changed	
		if (index == 0) {
			return removeFront();
		}

		// access the node to the immediate LEFT of the node to be removed
		ListNode currNode = this.head;
		for (int i=0; i<(index-1); i++) {
			currNode = currNode.getNext();
		}

		ListNode nodeToRemove = currNode.getNext();
		// save the item held by the node to be removed
		int result = nodeToRemove.getItem();

		// point the current node to the node after the node to be removed
		currNode.setNext(nodeToRemove.getNext());

		numberOfItems--;
		return result;
	}

	// remove 1st item and return it
	public int removeFront() {
		// get the item held by the first node
		int result = this.head.getItem();
		// set the head of this linkedlist to the node that the head was pointing to
		this.head = this.head.getNext();

		numberOfItems--;
		return result;
	}

	// remove last item and return it
	public int removeBack() {
		return removeAtIndex(size()-1);
	}

	/* print out the list from index 0 to index N-1 */
	public void print() {
		System.out.print("List is: ");

		ListNode currNode = this.head;
		for (int i=0; i<numberOfItems; i++) {
			System.out.format("%d, ", currNode.getItem());
			currNode = currNode.getNext();
		}

		System.out.println("");
	}
}