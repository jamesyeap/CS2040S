public class FractionV1 {
	/* 
		Pre-condition: 2 fractions
		Post-condition: prints out the resulting fraction from the addition of the 2 fractions
	*/	

	public static void main(String[] args) {
		int a,b,c,d,newNum,newDenom;

		a = 1;
		b = 2;
		c = 3;
		d = 4;
		newDenom = b*d;
		newNum = a*d+c*b;

		System.out.println("New Fraction = " + newNum + "/" + newDenom); 
			// use concatenate operator, +, to combine strings into a single string
			// note: variable values will be converted to string automatically
	}
} 