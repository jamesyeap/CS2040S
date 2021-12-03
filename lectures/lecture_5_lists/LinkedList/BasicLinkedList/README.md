# List ADT (Abstract Data-Type)
Represented by the `ListInterface` in Java.

## Java `array`
The `array` class in Java is a Concrete Data-Type of `ListInterface`.

- Dynamic array 
	- array can be resized
	- see below for how this is achieved (TODO)
- Static array
	- size of array is fixed

### Basic Operations
3 main operations:
- Retrieval 
- Insertion 
- Deletion 

#### Retrieval
##### Time-Complexity
###### All-Cases
`O(1)`

#### Insertion 
##### Time-Complexity
###### Best-Case
`O(1)`
###### Worst-Case
`O(n)`

Better to use Amortized Time-Complexity here.
- Amortized Cost Per Run = (Total Number of Operations across `n` runs) / (`n`)
	- assumption: array size is DOUBLED when full
		- can try: don't DOUBLE, but just n+1
			- "will result in a bad Amortized Cost"

###### Average-Case


#### Deletion
##### Time-Complexity
###### Best-Case
###### Worst-Case
###### Average-Case

## LinkedList
- refer to Slide 25 for LinkedList implementation

If nothing is pointing to a LinkedList, then the entire LinkedList will be deleted.
- Java's Garbage Collector will reclaim the memory occupied.
- so, to prevent this, need a `head` to point to the first element of the linked-list.

### Accessing an item in a LinkedList
#### Time-Complexity
Best-case: `O(1)`
If you're accessing the head of the LinkedList.

Worst-Case: `O(n)`
If you're accessing the last item of the LinkedList.

### Inserting an item in a LinkedList
#### General Case
To insert a Node anywhere other than the START of the list.
```language
CREATE a new node
ACCESS the node at index (n-1)
	POINT the new node to the node at index (n)
	POINT the node at index (n-1) to the new node
```
To insert a Node at the BACK of the list, just access the last element of the LinkedList.
- so inserting at the BACK is not a Special Case.

#### Special Case 
To insert a Node at the START of the list.
```language
TODO
```

#### Time-Complexity
Best-Case: `O(1)`
Worst-Case: `O(n)`

Depends on the Time-Complexity of the function AccessItem

### Removing an item in a LinkedList
#### General Case 
To remove a Node anywhere other than the START of the list.
```language
ACCESS the node at index (n-1)
	POINT the node at index (n-1) to the node at index (n+1)
```
#### Special Case 
To remove a Node at the START of the list.
```language
POINT the head to the node at index 1
```

#### Time-Complexity
Best-Case: `O(1)`
Worst-Case: `O(n)`

Depends on the Time-Complexity of the function AccessItem


