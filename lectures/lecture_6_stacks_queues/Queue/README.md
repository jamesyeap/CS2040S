# 2 Implementations for Queue
Same as everything else; there is an Array implementation and LinkedList implementation.
- front
- back

## Array implementation
- maxSize

### Circular Array
Just because `back` is greater than `maxSize`, doesn't mean the Queue is full.\
There may be empty space in front if elements have been dequeued. 

```java
front = (front+1) % maxsize;
back = (back+1) % maxsize;
```

### Ambiguous Full/Empty state
Cannot use `front == back` to check if Queue is full or empty.

Solution 1\
Maintain queue size of full staus.

Solution 2 (used in our codes)\
Leave a gap between B and F.

Full Case: (((B+!) & maxsize) == F)
Empty Case: F == B

## LinkedList implementation
...


# Uses for Queue
- Print queue
- Simulations
	- Perform the simulations in chronological order
- Breadth-first traversal of graph
- Checking palindromes 
	- "for illustration only as it is not a real application of Queue"

	
# Package in Java
`LinkedList` package in Java actually implements a Queue.