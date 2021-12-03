import java.util.Scanner;
import java.util.Arrays;

/**
 * 
 * 	PSEUDO-CODE 
 * 
 * 	INIT a fastest time = MAX_VALUE
 * 	INIT an array of length 4 to hold the runners' names
 * 	
 *	FOR each runner given
 * 		INIT a total time = 0
 * 		ADD this runner's 1st-leg timing to the total time 
 *		
 * 		FOR all runners EXCEPT this current runner
 * 			INIT a list of length 3 to hold the runners' names and their respective 2nd-leg timings
 * 
 * 			IF there are empty slots in the list
 * 				ADD the runner's name and 2nd-leg timing to this list
 * 			ELSE 
 * 				IF the runner has a lower 2nd-leg timing than a specific runner in the list
 *					REPLACE that runner with this current runner 
 * 
 * 		ADD the 2nd-leg timings of the 3 runners selected to the total time
 * 
 * 		IF the total time is lower for this iteration is lower than the fastest time
 * 			REPLACE the fastest time with the total time for this iteration
 * 			REPLACE the array of names with the names of these 4 runners
 * 		
 * 	PRINT the fastest time and the names of the 4 runners
 * 
 */

public class BestRelayTeam {
	public static void main(String[] args) {
		double fastestTime = Double.MAX_VALUE; 
		Runner[] bestTeam = new Runner[4];
		int numberOfRunners;

		Scanner sc = new Scanner(System.in);
		numberOfRunners = sc.nextInt(); 
		sc.nextLine(); // moves the cursor to the next line

		Runner[] allRunners = new Runner[numberOfRunners];

		for (int i=0; i<numberOfRunners; i++) {
			String buffer = sc.nextLine();
			String[] splitBuffer = buffer.split(" "); 			

			allRunners[i] = new Runner(splitBuffer[0], 
										Double.parseDouble(splitBuffer[1]), 
										Double.parseDouble(splitBuffer[2]));
		}

		for (int i=0; i<numberOfRunners; i++) {
			Runner[] secondRunners = new Runner[3];
			int numberOfSecondRunners = 0;

			for (int j=0; j<numberOfRunners; j++) {
				if (i != j) {
					Runner currentRunner = allRunners[j];

					if (numberOfSecondRunners < 3) {
						secondRunners[numberOfSecondRunners] = currentRunner;

						numberOfSecondRunners++;
					} else {
						int indexOfSlowestRunner = 0;

						for (int k=0; k<3; k++) {
							if (secondRunners[k].secondLeg > secondRunners[indexOfSlowestRunner].secondLeg) {
								indexOfSlowestRunner = k;
							}
						}

						if (secondRunners[indexOfSlowestRunner].secondLeg > currentRunner.secondLeg) {
							secondRunners[indexOfSlowestRunner] = currentRunner;
						}
					}
				}
			}

			// calculate the total time this permutation of team would take
			double totalTime = allRunners[i].firstLeg;
			for (int j=0; j<3; j++) {
				totalTime += secondRunners[j].secondLeg;
			}

			if (totalTime < fastestTime) {
				// create a new best team
				Runner[] newBestTeam = new Runner[4];
				newBestTeam[0] = allRunners[i];
				for (int j=0; j<3; j++) {
					newBestTeam[j+1] = secondRunners[j]; 
				}

				bestTeam = newBestTeam;
				fastestTime = totalTime; 
			}
		}

		System.out.println(String.format("%f", fastestTime));
		for (int i=0; i<bestTeam.length; i++) {
			System.out.println(bestTeam[i].name);
		}
	}
}

class Runner {
	public String name;
	public double firstLeg;
	public double secondLeg;

	public Runner(String name, double firstLeg, double secondLeg) {
		this.name = name;
		this.firstLeg = firstLeg;
		this.secondLeg = secondLeg;
	}

	@Override
	public String toString() {
		return this.name;
	}

}