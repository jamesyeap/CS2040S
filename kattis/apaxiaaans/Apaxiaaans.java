import java.util.Scanner;

public class Apaxiaaans {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String input = sc.nextLine();
		char[] split = input.toCharArray();
		char[] result = new char[split.length];
		int nChar = 0;

		char lastChar = '\u0000';
		for (int i=0; i<split.length; i++) {
			if (split[i] != '\u0000' && split[i] != lastChar) {
				result[nChar] = split[i];				

				nChar++;
				lastChar = split[i];
			}
		}


		System.out.println(String.valueOf(result));
		//	test null check
		//	System.out.println(result[2] == '\u0000'); 

	}
}
