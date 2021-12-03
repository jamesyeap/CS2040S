import java.util.*;

public class ShatteredCake {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int width = sc.nextInt();
		sc.nextLine();

		int n = sc.nextInt();
		sc.nextLine();

		int area = 0;

		for (int i=0; i<n; i++) {
			int currWidth = sc.nextInt();
			int currLength = sc.nextInt();
			sc.nextLine();

			int currArea = currWidth * currLength;
			area += currArea;
		}

		int length = area / width;

		System.out.println(length);
	}
}
