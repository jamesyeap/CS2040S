import java.util.Stack;
import java.util.Scanner;

public class Delimitersoup {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int nChar = sc.nextInt();
		sc.nextLine();

		char[] allTokens = sc.nextLine().toCharArray();		

		Stack<Character> tokenStack = new Stack<>();

		for (int i=0; i<nChar; i++) {
			char token = allTokens[i];

			if (token == '(' | token == '{' | token == '[') {
				tokenStack.push(token);
			} else if (token == ' ') {
				// do nothing
			} else {
				Character topToken = tokenStack.isEmpty() ? null : tokenStack.pop();			

				boolean error = false;

				if (topToken == null) {
					error = true;
				} else {
					if (token == ')') {
						if (topToken != '(') {
							error = true;
						}
					}

					if (token == '}') {
						if (topToken != '{') {
							error = true;
						}
					}

					if (token == ']') {
						if (topToken != '[') {
							error = true;
						}
					}
				}

				if (error == true) {
					System.out.format("%c %d\n", token, i);
					return;
				}
			}
		}

		System.out.println("ok so far");
	}
}
