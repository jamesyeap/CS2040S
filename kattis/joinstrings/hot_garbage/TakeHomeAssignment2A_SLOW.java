import java.util.*;

public class TakeHomeAssignment2A_SLOW {
	public static String[] toPrint; 
	public static ArrayList<Queue<Integer>> anyAfter;
	public static Integer startFrom;
	public static Scanner sc;

	public static int[] test;
	public static int currPos;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		startFrom = 1;

		int numberOfStrings = sc.nextInt();
		sc.nextLine();

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
			toPrint[i] = sc.nextLine();
		}

		while (sc.hasNextInt()) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			Queue<Integer> whatsAfterThis;

			if (anyAfter.get(a) == null) {
				whatsAfterThis = new LinkedList<Integer>();
				anyAfter.set(a, whatsAfterThis);
				whatsAfterThis.offer(b);

				// System.out.format("Initialized a Queue for %d\n", a);
				// System.out.format("Adding %d to Queue for %d\n", b, a);
				System.out.println(anyAfter);
			} else {
				whatsAfterThis = anyAfter.get(a);
				whatsAfterThis.offer(b);

				// System.out.format("Adding %d to Queue for %d\n", b, a);
				System.out.println(anyAfter);
			}

			startFrom = a; 
		}

		// System.out.println(anyAfter);
		System.out.println("---");

		test = new int[numberOfStrings];
		currPos = 0;

		start(startFrom);

		System.out.println(Arrays.toString(test));

		for (int i=0; i<numberOfStrings; i++) {
			System.out.print(toPrint[test[i]]);
		}
	}

	static void start(int index) {
		Queue<Integer> whatsAfterThis = anyAfter.get(index);
		// System.out.print(toPrint[index]);
		test[currPos] = index;
		currPos++;

		System.out.format("printing index: %d | %s\n", index, anyAfter);

		int nextIndex = index;
		while (whatsAfterThis != null && !whatsAfterThis.isEmpty()) {			
			nextIndex = whatsAfterThis.poll();			
			start(nextIndex);	
		}
		
		System.out.println();
	}
}