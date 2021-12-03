import java.io.*;
import java.util.*;

public class TakeHomeAssignment2A {
	public static String[] toPrint; 
	public static ArrayList<Queue<Integer>> anyAfter;
	public static Integer startFrom;
	// public static Scanner sc;
	public static BufferedReader br;
	public static PrintWriter pw;

	public static int[] test;
	public static int currPos;

	public static void main(String[] args) throws IOException {
		// sc = new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out), 1000000));
		startFrom = 1;

		// int numberOfStrings = sc.nextInt();
		// sc.nextLine();
		int numberOfStrings = Integer.parseInt(br.readLine().split("")[0]);

		//	INIT two data-structures to:
		//		STORE the strings to be printed
		//		STORE the order in which they are to be printed
		toPrint = new String[numberOfStrings+1];
		anyAfter = new ArrayList<Queue<Integer>>(numberOfStrings+1);
		for (int i=0; i<numberOfStrings+1; i++) {
			anyAfter.add(i, null);	// initialize all Queues to 'null'
		}

		//	STORE the strings to be printed
		for (int i=1; i<numberOfStrings+1; i++) {
			// toPrint[i] = sc.nextLine();
			toPrint[i] = br.readLine();
		}


		String command;
		String[] splitCommand;
		for (int i=0; i<numberOfStrings-1; i++) {
			command = br.readLine();
			splitCommand = command.split(" ");

			int a = Integer.parseInt(splitCommand[0]);
			int b = Integer.parseInt(splitCommand[1]);

			Queue<Integer> whatsAfterThis;

			if (anyAfter.get(a) == null) {
				whatsAfterThis = new LinkedList<Integer>();
				anyAfter.set(a, whatsAfterThis);
				whatsAfterThis.offer(b);

				// System.out.format("Initialized a Queue for %d\n", a);
				// System.out.format("Adding %d to Queue for %d\n", b, a);
			} else {
				whatsAfterThis = anyAfter.get(a);
				whatsAfterThis.offer(b);

				// System.out.format("Adding %d to Queue for %d\n", b, a);
			}

			startFrom = a; 
		}

		test = new int[numberOfStrings];
		currPos = 0;

		start(startFrom);

		// pw.println(Arrays.toString(test));
		for (int i=0; i<numberOfStrings; i++) {
			pw.print(toPrint[test[i]]);
		}

		pw.flush();
	}

	static void start(int index) {
		Queue<Integer> whatsAfterThis = anyAfter.get(index);
		// System.out.print(toPrint[index]);
		// pw.print(toPrint[index]);
		test[currPos] = index;
		currPos++;
		
		int nextIndex;
		while (whatsAfterThis != null && !whatsAfterThis.isEmpty()) {
			nextIndex = whatsAfterThis.poll();
			start(nextIndex);
		}

	}
}