class Autoboxing {
	public static void main(String[] args) {
		int i = new Integer(5); // unboxing
		Integer intObj = 7; // autoboxing

		System.out.println("i = " + i);
		System.out.println("intObj = " + intObj);

		//  Note that the standard way to instantiate an object of any wrapper
		//		class is to use autoboxing instead of the "new" keyword
	}
}