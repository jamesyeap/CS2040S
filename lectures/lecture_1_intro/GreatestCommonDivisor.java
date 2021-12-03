import java.util.Scanner;

class GreatestCommonDivisor {

	// Returns GCD of e and f
	// Pre-cond: e and f must be > 0
	public static int gcd(int e, int f) {
		/* note: all parameters in Java are passed by value
			- a copy of the actual argument is created upon method invocation
			- the method parameter and its corresponding actual argument are 2 independent variables
				- to let the method modify the actual argument, a reference data type is needed
		*/

		int rem;

		while (f > 0) {
			rem = e%f;
			e = f;
			f = rem;
		} 

		return e;
	}

	public static void main(String[] args) {
		int a,b;
		Scanner sc = new Scanner(System.in);

		a = sc.nextInt();
		b = sc.nextInt();

		System.out.printf("%i\n", gcd(a, b));
	}
}