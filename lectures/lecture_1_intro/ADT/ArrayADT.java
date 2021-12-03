/*
 * An element in an array is accessed by its index in O(1) time. 
 * The length of an array, array.length, is set and fixed at the time of creation.
 */

class ArrayADT {
  public static void main(String[] args) {
    String[] clothes = new String[5]; // initializes an Array of length 5 
    clothes[0] = "shirt";

    System.out.println(clothes.length); // prints "5"
    System.out.println(clothes[2]); // prints "null"
  }
}
