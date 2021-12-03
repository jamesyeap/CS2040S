import java.util.*;

public class Akcija {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int nBooks = Integer.parseInt(sc.nextLine());

		int[] bookArr = new int[nBooks];
		for (int i=0; i<nBooks; i++) {
			bookArr[i] = Integer.parseInt(sc.nextLine());

			// System.out.println(bookArr[i]);
		}

		Arrays.sort(bookArr);

		int totalCost = 0;
		int totalDiscount = 0;
		int[] bookGroup = new int[3];
		int bookGroupLen = 0;

		for (int i=bookArr.length-1; i>=0; i--) {
			// System.out.format("currBook: %d, bookGroupLen: %d\n", bookArr[i], bookGroupLen);		

			totalCost += bookArr[i];
			bookGroup[bookGroupLen] = bookArr[i];
			bookGroupLen++;

			if (bookGroupLen == 3) {
				int cheapestBook = bookGroup[0];

				for (int j=1; j<3; j++) {
					if (cheapestBook > bookGroup[j]) {
						cheapestBook = bookGroup[j];
					}
				}

				// System.out.format("discount applied: %d\n", cheapestBook);

				totalDiscount += cheapestBook;

				bookGroupLen = 0;
				bookGroup = new int[3]; // reset book group to empty array
			} 	
		}
		System.out.println(totalCost - totalDiscount);
	}
}
