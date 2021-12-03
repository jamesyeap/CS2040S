public class DataTypeConversion {
	public static void main(String[] args) {
		double d;
		int i;
		i = 31415;

		// d = i / 10000; // 3.0
		// d = (int) i / 10000; // 3.0
		d = (float) i / 10000; // 3.1414999961853027

		System.out.println(d);
	}
}