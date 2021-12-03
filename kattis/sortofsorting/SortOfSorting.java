/* PSEUDO-CODE

READIN nTestCases

IF nTestCases == 0
	EXIT

ELSE 
	INIT resultArr[nTestCases]

	FOR int i=0 to nTestCases
		SET resultArr[i] = nextLine

	CALL Arrays.sort(resultArr, comp)
		** comp is a Comparator that will compare the elements inside resultArr using 
			the substring of their first 2 characters **

	PRINT all elements inside the sorted array

	GOBACK to the first step	
*/

import java.util.Arrays;
import java.util.Comparator;
import java.io.*; // import this to use BufferedReader, BufferedWriter, etc...

class sortByFirstTwoChar implements Comparator<String> {

	public int compare (String strA, String strB) {
		String subStrA = strA.substring(0, 2);
		String subStrB = strB.substring(0, 2);

		return subStrA.compareTo(subStrB);
	}
}

public class SortOfSorting {

	//	need to specify "throws IOException" for BufferedReader
	public static void main(String[] args) throws IOException {

		//	much faster way to read inputs compared to Scanner
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//	much faster way to write outputs
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String buffer;
		String[] splitBuffer;

		//	read in number of test cases to process
		buffer = br.readLine();
		splitBuffer = buffer.split(" ");
		int nTestCases = Integer.parseInt(splitBuffer[0]); //	only has 1 element, which is an integer

		while (nTestCases != 0) {

			String[] resultArr = new String[nTestCases];

			for (int i=0; i<nTestCases; i++) {
				resultArr[i] = br.readLine();
			}

			Arrays.sort(resultArr, new sortByFirstTwoChar());

			for (int i=0; i<nTestCases; i++) {
				pw.println(resultArr[i]);
			}

			pw.println("");

			//	read in number of test cases again
			buffer = br.readLine();
			splitBuffer = buffer.split(" ");
			nTestCases = Integer.parseInt(splitBuffer[0]);
		}

		//	always call .flush() or .close() on the PrintWriter before
		//		exiting your program, or else some outputs may not be printed
		pw.flush();
	}
}
