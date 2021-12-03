class ListNode {
	private int item; 
	private ListNode next; 

	/* constructors */
	public ListNode(int item) {
		this(item, null);
	}

	public ListNode(int item, ListNode next) {
		this.item = item;
		this.next = next;
	}

	/* actions */
	public int getItem() {
		return item;
	}

	public ListNode getNext() {
		return next;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}
}