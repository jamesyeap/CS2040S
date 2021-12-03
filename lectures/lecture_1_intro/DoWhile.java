class DoWhile {
	public static void main(String[] args) {
		boolean sayHello = false;

		/* do-while:
				execute body before condition checking
		*/
		do {
			System.out.println("Hello");
		} while (sayHello == true); // "Hello"

		/* while:
				check condition before executing body
		*/
		while (sayHello == true) {
			System.out.println("Hello") // doesn't print anything
		} 
	}
}