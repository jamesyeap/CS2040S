# Lecture 4: Sorting

## Categories of Sorting Algorithms
### Comparison based and Iterative algorithms

<details>
<summary>Selection Sort</summary>

</details>
2. Bubble Sort
3. Insertion Sort

### Comparison based and Recursive algorithms 
1. Merge Sort
2. Quick Sort

### Non-Comparison based algorithms
1. Radix Sort
- 2 directions 
	- least-significant to most-significant
	- most-significant to least-significant
- "most important place should be sorted last" - (why? TODO)

- works for
	- integers
	- floating-point (some only)
		- if there is a limited number of decimal places, then Radix Sort CAN be used
		- however, if there is no fixed-precision (read: unlimited decimal places), then Radix Sort CANNOT be used
- assumes that all integers/floating-points in the array have the SAME NUMBER of digits

Time-Complexity:
where `d` is the maximum number of digits of `n` numeric strings in the array, the Time-Complexity is:

## [Case 1] if `d` can be bounded:
`O(n)`

## [Case 2] if `d` cannot be bounded:
`O(d * n)`

### Example
if the numeric strings are in the array, where `n` is the length of the array, are from `1` to `n^n`, then `d` cannot be bounded
	- because the maximum number of digits, `d`, is `log10(n^n)`
		- `d` is therefore not a constant
		- thus, `d` cannot be bounded


## Comparison of Sorting Algorithms
2 additional aspects for comparing sorting algorithms.

### In-Place
A sorting algorithm is said to be an In-Place sort if it requires only a CONSTANT amount, i.e. `O(1)`, of extra space during the sorting process.

#### Looks at amount of extra space used PER-STEP
In-Place looks at the the amount of extraspace used PER-STEP.

##### Merge-Sort is NOT In-Place
Due to the temporary array used in the merge-step.
- Size of the temporary array is not constant.
	- Is the size of the subarray
	- Space: O(j-i)
... TODO

##### Radix-Sort is NOT In-Place (TODO: Why?)
...

##### Note: Quick-Sort IS In-Place
But why? Isn't Quick-Sort a recursive algorithm, and therefore it should NOT be in-place?

Quick-Sort is considered in-place because in-place looks at the amount of space required PER-CALL.
- Quick-Sort satisfies this because it uses a CONSTANT amount of space PER-CALL
- Don't quite understand this: TODO

### Stable
A sorting algorithm is Stable if the relative order of elements with the same key value is preserved by the algorithm.

##### Quick-Sort is NOT Stable

##### Selection-Sort is NOT Stable

##### Note: Java's Sort IS Stable (Tim-Sort) Q: What is it? TODO


## Sorting in Java
### Sorting `List<>`

#### Sorting Primitive types and `String`
```Java
List<String> lst = Arrays.asList(new String[]{"We", "walk", "the", "line"});
Collections.sort(lst);
System.out.println(lst); // [We, line, the, walk]
```

#### Sorting custom classes
Use `Comparator` 
- see below

### Sorting `array`
```Java
String[] arr = new String[]{"We", "walk", "the", "line"};
Arrays.sort(arr);
System.out.println(lst); // [We, line, the, walk]
```

### Using Comparator
Comparator is an Interface that requires 2 functions to be defined: `compare` and `equals`
```Java
import java.util.Comparator; 

class SomeClassComparator implements Comparator<SomeClass> {
	public int compare(SomeClass o1, SomeClass o2) {
		//	VERSION 1
		//		do the comparison yourself
		return o1.getAttr() - o2.getAttr(); 

		//	VERSION 2
		//		use "compareTo()"
		return o1.getAttr().compareTo(o2.getAttr());
	}

	public boolean equals(Object o) { // parameter type is always "Object"
		return this == obj;
	}
}
```

