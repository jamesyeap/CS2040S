import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		String test = "foo  bar";
		char[] splitTest = test.toCharArray();

		for (int i=0; i<splitTest.length; i++) {
			int asciiVal = splitTest[i];
			System.out.format("%c - %d\n", splitTest[i], asciiVal);
		}
	}
}