/**
 
PSEUDO-CODE
 
GET number of test cases
INIT an empty array of characters, result

INIT an array of characters, of length 256, dict
INIT an integer variable, count = 0;
INIT an integer variable, keypadNum = 2;

FOR the range of values from 97 to 122 (inclusive)
	IF keypadNum = 7 OR keypadNum = 9
   		INIT a string variable, stringRep

		FOR range 0 to count (inclusive)
			APPEND the string version of keypadNUM to stringRep

		ADD stringRep to the ith position of dict

		INCREMENT count by 1
		
		IF count >= 4
			INCREMENT keypadNum by 1
			RESET count = 0
	
	ELSE
		INIT a string variable, stringRep

		FOR range 0 to count (inclusive)
			APPEND the string version of keypadNum to stringRep

		ADD stringRep to the ith position of dict

		INCREMENT count by 1
		
		IF count >= 3
			INCREMENT keypadNum by 1
			RESET count = 0

SET dict[32] to 0
		
FOR each test case
	INIT an empty String variable, result

	GET the line of text
	SPLIT the line of text into an array of characters		

	INIT an integer variable, prevKeySet = 0;

	FOR each character
		APPEND the output of arr['character'] to result
		GET the ASCII value of 'character', asciiVal
			IF asciiVal is between 'p' and 's' (inclusive) OR IF asciiVal is between 'w' and 'z' (inclusive)
				IF the quotient of (asciiVal - 1) / 4 is equal to keySet
					APPEND a "Space" character to result

				SET keySet to the quotient of (asciiVal - 1) / 4
			ELSE 	
				IF the quotient of (asciiVal - 1) / 3 is equal to keySet
					APPEND a "Space" character to result

				SET keySet to the quotient of (asciiVal - 1) / 3

	PRINT result in a new line
 */
  	
import java.util.Scanner; 
import java.util.Arrays;

public class T9Spelling {
	public static void main(String[] args) {
		/*	create a dict that maps the ASCII values of characters to 
			the button press strings */

		String[] dict = new String[256]; 

		int keypadNum = 2;
		int count = 0;
		for (int i='a'; i<='z'; i++) {
			String stringRep = "";

			if (keypadNum == 7 || keypadNum == 9) {
				for (int j=0; j<=count; j++) {
					stringRep += Integer.toString(keypadNum);
				}

				count++;

				if (count >= 4) {
					keypadNum++;
					count = 0;
				}
			} else {
				for (int j=0; j<=count; j++) {
					stringRep += Integer.toString(keypadNum);
				}

				count++;

				if (count >= 3) {
					keypadNum++;
					count = 0;
				}
			}

			dict[i] = stringRep;
		}

		dict[32] = "0";

		/* start converting user input */
		Scanner sc = new Scanner(System.in);

		//	get the number of test cases
		int nTestCases = sc.nextInt();
		//	move cursor to the next line
		sc.nextLine();

		for (int i=0; i<nTestCases; i++) {
			String buffer = sc.nextLine();
			char[] charArr = buffer.toCharArray();

			String result = "";			
			Character prevKey = null;

			for (int j=0; j<charArr.length; j++) {
				int asciiVal = charArr[j];
				String stringRep = dict[asciiVal];

				if (prevKey != null && prevKey.equals(stringRep.charAt(0))) {
					result += " ";
				}

				result += stringRep;
				prevKey = stringRep.charAt(0);
			}

			System.out.format("Case #%d: %s\n", i+1, result);
		}
	}
}
