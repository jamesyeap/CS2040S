class CircularLinkedList {
    public int size;
    public ListNode head;
    public ListNode tail;

    public CircularLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    public void addFirst(int element) {
        size++;
        head = new ListNode(element, head);

        if (tail == null) {
            tail = head;
        }

        tail.setNext(head);
    }

    public void add(int element) {
        if (size == 0) {
            addFirst(element);
        } else {
            //  get the last node
            ListNode lastNode = head;
            int lastPos = 0;

            while (lastPos != size-1) {
                lastNode = lastNode.getNext();
                lastPos++;
            }

            ListNode newNode = new ListNode(element, head);
            lastNode.setNext(newNode);

            size++;
        }
    }

    public void swap(int index) {
        //  take the modulus of the given index 
        //      because the index can wrap around
        int modIndex = index % size; 

        if (modIndex == 0) {
            //  CASE 1: swap the 1st and 2nd item

            //  get the last node
            ListNode lastNode = head;
            int lastPos = 0;
            while (lastPos != size-1) {
                lastNode = lastNode.getNext();
                lastPos++;
            }

            ListNode secondNode = head.getNext();
            lastNode.setNext(secondNode);

            head.setNext(secondNode.getNext());
            secondNode.setNext(head);

            head = secondNode;
        } else if (modIndex == size-1) {
            //  CASE 2: swap the LAST and FIRST item

            //  get the second-last node
            ListNode secondLastNode = head;
            int secondLastPos = 0;
            while (secondLastPos != size-2) {
                secondLastNode = secondLastNode.getNext();
                secondLastPos++;
            }

            ListNode lastNode = secondLastNode.getNext();

            lastNode.setNext(head.getNext());
            head.setNext(lastNode);
            secondLastNode.setNext(head);
            head = lastNode;
        } else {
            //  CASE 3: swap the (i)th and (i+1)th item

            //  get the (i-1)th item
            ListNode prevNode = head;
            int prevPos = 0;
            while (prevPos != (modIndex-1)) {
                prevNode = prevNode.getNext();
                prevPos++;
            }

            ListNode currNode = prevNode.getNext();
            ListNode nextNode = currNode.getNext();

            prevNode.setNext(nextNode);
            currNode.setNext(nextNode.getNext());
            nextNode.setNext(currNode);

            //  Illustration
                /* ... | prevNode | currNode | nextNode | ... */
                /* ... |    i-1   |     i    |    i+1   | ... */
        }
    }

    public void print() {
        ListNode currNode = head;

        System.out.print("[ ");

        for (int i=0; i<size; i++) {
            System.out.format("%d, ", currNode.getItem());
            currNode = currNode.getNext();
        }

        System.out.format("]\n");
    }
}