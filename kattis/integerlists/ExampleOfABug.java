import java.util.*;
import java.io.*;

public class Test {
	public static void main(String[] args) throws IOException {
		// Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int nTestCases = Integer.parseInt(br.readLine().split("")[0]); // <---------- the problem is here!
		// sc.nextLine();

		for (int i=0; i<nTestCases; i++) {
			char[] commands = br.readLine().toCharArray();
			
			int nElems = Integer.parseInt(br.readLine().split("")[0]); // <----------- the problem is here!
			// System.out.format("nElems: %d\n", nElems);
			// sc.nextLine();

			Deque<Integer> elemList = new LinkedList<Integer>();
			
			String buffer = br.readLine();
			String[] splitBuffer = buffer.split(",");

			for (int j=0; j<nElems; j++) {
				int currElem = Integer.parseInt(splitBuffer[j].replace("[", "").replace("]", ""));
				// System.out.println(currElem);

				elemList.add(currElem);
			}


			boolean anyError = false;
			boolean reversed = false;
			for (int j=0; j<commands.length; j++) {
				if (commands[j] == 'R') {
					reversed = !reversed;

				} else { // commands[j] == 'D'

					if (elemList.isEmpty()) {
						anyError = true;

						break;
					} else {
						if (reversed) {
							elemList.pollLast();
						} else {
							elemList.pollFirst();
						}
					}
				}
			}

			if (anyError == true) {				
				pw.println("error");
				
			} else {
				if (reversed) {
					Deque<Integer> revList = new LinkedList<Integer>();
					while (!elemList.isEmpty()) {
						revList.add(elemList.pollLast());
					}

					pw.println(revList.toString().replace(" ", ""));
				} else {
					pw.println(elemList.toString().replace(" ", ""));
				}
			}
			pw.flush();
		}
	}
}