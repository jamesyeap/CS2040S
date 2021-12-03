# 2 ways to implement a List
There are 2 ways to implement a List ADT. 

The specifications for a List ADT is found in `ListInterface.java`
- states all the operations that a List ADT has to support

## Array implementation (1 variation only)
[ArrayList](/ArrayList)

## LinkedList implementations (4 variations)
[BasicLinkedList](/BasicLinkedList)\
[TailedLinkedList](/TailedLinkedList)\
[CircularLinkedList](/CircularLinkedList)\
[DoublyLinkedList](/DoublyLinkedList)\

# When to use which implementation?
It depends on the problem, but here are 4 scenarios.

### Only need to add/remove to front of the list 
Use LinkedList

### Only need to add/remove to back of the list
Use Array
- especially if we know maximum number of items in advance, just create a large enough array from start

### If we need to add/remove anywhere in the list
Equal chance of adding to any index 
- both LinkedList or Array is the same
If we need to keep adding/removing to a particular index i in the list 
- Use LinkedList
- maintain a reference to the node at index i-1, all insertions/deletions thereafter is only O(1) time

### If we have few insertions/deletions but a lot of accesses
Use Array since accessing any item is O(1) time