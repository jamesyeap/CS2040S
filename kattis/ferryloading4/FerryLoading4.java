import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class FerryLoading4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int nTestCases = sc.nextInt();
		sc.nextLine();

		for (int i=0; i<nTestCases; i++) {
			int lengthFerry = sc.nextInt() * 100; // in centimeters
			int mCarsWaiting = sc.nextInt();
			sc.nextLine();

			Queue<Integer> leftBank = new LinkedList<Integer>();
			Queue<Integer> rightBank = new LinkedList<Integer>();

			int nTrips = 0;

			for (int j=0; j<mCarsWaiting; j++) {
				String[] splitLine = sc.nextLine().split(" ");
				int lengthCar = Integer.parseInt(splitLine[0]); // in centimeters
				String side = splitLine[1];

				if (side.equals("left")) {					
					leftBank.offer(lengthCar);
				} else { // side.equals("right")
					rightBank.offer(lengthCar);
				}
			}

			boolean ferryOnLeftBank = true;
			while (!leftBank.isEmpty() | !rightBank.isEmpty()) {
				if (ferryOnLeftBank) {
					// System.out.println("leftBank, before loading: " + leftBank.toString());
					int lengthFerryRemaining = lengthFerry;

					while (!leftBank.isEmpty()) {
						if (lengthFerryRemaining - leftBank.peek() >= 0) {
							lengthFerryRemaining = lengthFerryRemaining - leftBank.poll();
						} else {
							break;
						}
					}

					// System.out.println("leftBank, after loading: " + leftBank.toString());
					nTrips++;
					ferryOnLeftBank = !ferryOnLeftBank;
				} else {
					// System.out.println("rightBank, before loading: " + rightBank.toString());
					int lengthFerryRemaining = lengthFerry;

					while (!rightBank.isEmpty()) {
						if (lengthFerryRemaining - rightBank.peek() >= 0) {
							lengthFerryRemaining = lengthFerryRemaining - rightBank.poll();
						} else {
							break;
						}
					}

					// System.out.println("rightBank, after loading: " + rightBank.toString());
					nTrips++;
					ferryOnLeftBank = !ferryOnLeftBank;
				}
			}

			System.out.println(nTrips);
		}
	}
}
