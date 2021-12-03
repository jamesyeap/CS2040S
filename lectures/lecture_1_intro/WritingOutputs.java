class WritingOutputs {
	public static void main(String[] args) {
		/* 3 methods
				- System.out.print()
				- System.out.println()
				- System.out.printf()
					Syntax: %[-][W].[P]type
						-: For left alignment 
						W: For width
						P: For precision

					%d: for integer value
					%f: for double floating-point value
					%s: for string
					%b: for boolean value
					%c: for character value

		*/

		/* Examples */
		System.out.print("ABC");
		System.out.println("DEF"); // prints "ABCDEF"

		System.out.println("GHI"); // prints "GHI"

		System.out.printf("Very C-like %.3f\n", 3.14159); // prints "Very C-like 3.142"
	}
}