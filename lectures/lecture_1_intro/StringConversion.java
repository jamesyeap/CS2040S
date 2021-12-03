class StringConversion {
	public static void main(String[] args) {

		/* convert "String" to "int" */
		int num = Integer.valueOf("28");
		System.out.println(num); // "28"

			/* note: you cannot call "getClass()" on primitive types 
				num.getClass() // "error: int cannot be dereferenced"
			*/

		/* convert "int" to "String" */
		String str = Integer.toString(567);
		System.out.println(str.getClass()); // "class java.lang.String"
	}
}