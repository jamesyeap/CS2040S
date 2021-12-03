import java.util.Scanner; 

class ReadingInputs {
	public static void main(String[] args) {
		/* Declaration of Scanner variable
				- constructs a "Scanner" object
				- attaches it to the standard input "System.in" (the keyboard)
					- the variable "sc" will then receive input from this source
					- the "Scanner" object can be attached to other input sources besides the keyboard
				- typically, only one "Scanner" object is needed, even if many input values are to be read
					- the same "Scanner" object can be used to call the relevant methods to read input values
		*/
	
		Scanner scVar = new Scanner(System.in);

		/* Functionality provided */
		scVar.nextInt(); // read an integer value from source System.in
		scVar.nextDouble(); // read a double value from source System.in

		/* Example */
		int a;
		double b;

		a = scVar.nextInt();
		b = scVar.nextDouble();

		System.out.println("a = " + a); // prints "3"
		System.out.println("b = " + b); // prints "4.0"
	}
}