import java.util.*;

public class Password {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int nPossiblePasswords = Integer.parseInt(sc.nextLine());

		Double[] probabilities = new Double[nPossiblePasswords];
		for (int i=0; i<nPossiblePasswords; i++) {
			probabilities[i] = Double.parseDouble(sc.nextLine().split(" ")[1]);
		}

		int attempt = 1;
		double expectedNumberOfAttempts = 0;

		//	sort probabilities; highest -> lowest
		Arrays.sort(probabilities, Collections.reverseOrder());

		for (int i=0; i<nPossiblePasswords; i++) {
			expectedNumberOfAttempts += (probabilities[i] * attempt);
			attempt++;
		}

		System.out.println(expectedNumberOfAttempts);
	}
}
