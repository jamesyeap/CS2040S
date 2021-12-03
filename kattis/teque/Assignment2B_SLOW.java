import java.util.*;

public class Assignment2B_SLOW {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int nOps = sc.nextInt();
		sc.nextLine();

		Integer[] frontArr = new Integer[2 * nOps];
		int frontArrHead = nOps / 2;
		// Integer[] frontArr = new Integer[1000000];
		// int frontArrHead = 500000;
		int frontArrSize = 0; 

		Integer[] backArr = new Integer[2 * nOps];
		int backArrHead = nOps / 2;
		// Integer[] backArr = new Integer[1000000];
		// int backArrHead = 500000;
		int backArrSize = 0;

		String op;
		String[] splitOp;
		String command;
		int arg;

		for (int i=0; i<nOps; i++) {
			op = sc.nextLine();
			splitOp = op.split(" ");
			command = splitOp[0];
			arg = Integer.parseInt(splitOp[1]);

			System.out.format("COMMAND: [%s %d]\n", command, arg);

			if (command.equals("get")) {
				int index = arg;
				int element;

				if (index >= frontArrSize) {
					element = backArr[backArrHead + (index - frontArrSize)];
				} else {
					element = frontArr[frontArrHead + index];
				}

				System.out.println(Arrays.toString(frontArr) + Arrays.toString(backArr));
				System.out.println(element);
			} else if (command.equals("push_front")) {
				int element = arg;

				if (frontArrSize <= backArrSize) {
					frontArrHead--;
					frontArr[frontArrHead] = element;
					frontArrSize++;

					System.out.println("[AFTER PUSH]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));
				} else {
					//	shift the LAST element of the frontArr to 
					//		the FRONT of the backArr
					System.out.println("---------- shift the LAST element of the frontArr to the FRONT of the backArr ----------");
					System.out.println("[BEFORE BALANCE]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));
					backArrHead--;
					backArr[backArrHead] = frontArr[frontArrHead+frontArrSize-1];
					backArrSize++;
					frontArr[frontArrHead+frontArrSize-1] = null; // for clarity
					System.out.println("[AFTER BALANCE]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));

					frontArrHead--;
					frontArr[frontArrHead] = element;
					System.out.println("[AFTER PUSH]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));
				}

			} else if (command.equals("push_back")) { 
				int element = arg;

				if (backArrSize < frontArrSize) {
					backArr[backArrHead+backArrSize] = element;
					backArrSize++;

					System.out.println("[AFTER PUSH]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));
				} else {
					backArr[backArrHead+backArrSize] = element;
					System.out.println("[AFTER PUSH]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));

					//	shift the FIRST element of the backArr to
					//		the BACK of the frontArr
					System.out.println("---------- shift the FIRST element of the backArr to the BACK of the frontArr ----------");
					System.out.println("[BEFORE BALANCE]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));
					frontArr[frontArrHead+frontArrSize] = backArr[backArrHead];
					frontArrSize++;
					backArr[backArrHead] = null; // for clarity
					backArrHead++;
					System.out.println("[AFTER BALANCE]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));
				}
				
			} else { // command.equals("push_middle")
				int element = arg;

				if (frontArrSize <= backArrSize) {
					frontArr[frontArrHead + frontArrSize] = element;
					frontArrSize++;

					System.out.println("[AFTER PUSH]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));
				} else {
					backArrHead--;
					backArr[backArrHead] = element;
					backArrSize++;

					System.out.println("[AFTER PUSH]:" + Arrays.toString(frontArr) + Arrays.toString(backArr));
				}
			}

			System.out.println();
		}

	}
}