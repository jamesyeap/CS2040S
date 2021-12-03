import java.util.Scanner;
import java.util.Arrays;

public class Skener {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int r = sc.nextInt();
		int c = sc.nextInt();
		int zR = sc.nextInt();
		int zC = sc.nextInt();

		// move cursor to the next line;
		sc.nextLine();

		char[][] result = new char[r * zR][c * zC];

		for (int i=0; i<r; i++) {
			//	FOR each line
			char[] splitLine = sc.nextLine().toCharArray();

			for (int j=0; j<c; j++) {				
				//	FOR each character in the line
				for (int k=(i*zR); k<(i*zR + zR); k++) {
					//	REPEAT this over zR rows
					for (int q=(j*zC); q<(j*zC + zC); q++) {
						//	COPY the character zC times in the line
						result[k][q] = splitLine[j];
					}
				}
			}
		}
		
		for (int i=0; i<(r*zR); i++) {
			for (int j=0; j<(c*zC); j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}

	}
}
