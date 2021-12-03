
[Tailed LinkedList](#tailed-linkedlist)\
[Doubly LinkedList](#doubly-linkedlist)

# Tailed LinkedList
Functions that are implemented differently are:
## accessing the last item

## inserting
Need to update tail when: 
### inserting to the end of the list

### inserting into an empty list

## removing

### removing from the end of the list
This operation is NOT O(1), because you still need to find the second-last item to point the tail to.
To find this second-last item, it is an O(n) operation, because you need to traverse the list to get this.

### removing from a list with only 1 element

# Doubly LinkedList
In this course, the Doubly LinkedList has a tail
- some mainstream implementations of the Doubly LinkedList do not have a tail

## Usefulness
Does not change the time complexity of List operations.
BUT improves the constant factor.
- which is important for labs; in particular beating the Time Constraint.


