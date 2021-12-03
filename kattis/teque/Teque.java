import java.util.*;
import java.io.*;

public class Teque {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int nOps = Integer.parseInt(br.readLine().split(" ")[0]);

		int[] frontArr = new int[2*nOps];
		int frontArrHead = nOps;
		int frontArrSize = 0; 

		int[] backArr = new int[2*nOps];
		int backArrHead = nOps;
		int backArrSize = 0;

		String op;
		String[] splitOp;
		String command;
		int arg;

		for (int i=0; i<nOps; i++) {
			op = br.readLine();
			splitOp = op.split(" ");
			command = splitOp[0];
			arg = Integer.parseInt(splitOp[1]);

			if (command.equals("get")) {
				int index = arg;
				int element;

				if (index >= frontArrSize) {
					element = backArr[backArrHead + (index - frontArrSize)];
				} else {
					element = frontArr[frontArrHead + index];
				}				

				pw.println(element);
			} else if (command.equals("push_front")) {
				int element = arg;

				if (frontArrSize <= backArrSize) {
					frontArrHead--;
					frontArr[frontArrHead] = element;
					frontArrSize++;

				} else {
					//	shift the LAST element of the frontArr to 
					//		the FRONT of the backArr
					backArrHead--;
					backArr[backArrHead] = frontArr[frontArrHead+frontArrSize-1];
					backArrSize++;
					frontArr[frontArrHead+frontArrSize-1] = 0; // set back to default value of 0 for clarity

					frontArrHead--;
					frontArr[frontArrHead] = element;
				}

			} else if (command.equals("push_back")) { 
				int element = arg;

				if (backArrSize < frontArrSize) {
					backArr[backArrHead+backArrSize] = element;
					backArrSize++;
				} else {
					backArr[backArrHead+backArrSize] = element;

					//	shift the FIRST element of the backArr to
					//		the BACK of the frontArr										
					frontArr[frontArrHead+frontArrSize] = backArr[backArrHead];
					frontArrSize++;
					backArr[backArrHead] = 0; // set back to default value of 0 for clarity
					backArrHead++;					
				}
				
			} else { // command.equals("push_middle")
				int element = arg;

				if (frontArrSize <= backArrSize) {
					frontArr[frontArrHead + frontArrSize] = element;
					frontArrSize++;					
				} else {
					backArrHead--;
					backArr[backArrHead] = element;
					backArrSize++;					
				}
			}			
		}

		pw.flush();
	}
}
