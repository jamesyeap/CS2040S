import java.util.Random;

import OwnAnalysis.*;

public class Q1f {
	public static String generateRandomString(int targetStringLength) {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}

	public static int h(String key) { 
		int pos = 0;
		for (int i = 0; i <= key.length()-1; i++) {
			pos += 9 * (int) key.charAt(i);
		}
		pos = pos % 54;

		return pos;
	}

	public static void main(String[] args) {
		int m = 54;
		Tracker table = new Tracker(m);

		for (int i=0; i<100; i++) {
			//	generate a random string of length 10
			int hashFunctionOutput = h(generateRandomString(10));

			table.incrementPosition(hashFunctionOutput);	
		}

		table.print();
	}
}