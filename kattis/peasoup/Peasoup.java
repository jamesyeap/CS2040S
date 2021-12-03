/**
 * Name: Yeap Yi Sheng James
 * Student Number: A0218234L
 * Lab Group: B08 
 */
 
import java.util.*;

public class Peasoup {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// GET the number of restaurants
		int n = sc.nextInt();		

		// FOR each restaurant 
		for (int i=0; i<n; i++) {
			// INIT the checking variables
			boolean matchPeasoup = false;
			boolean matchPancakes = false;

			// GET the number of menu-items 
			int k = sc.nextInt(); 
			sc.nextLine(); // <---- needed because of some weird thing with nextInt()

			// GET the name of the restaurant
			String restaurantName = sc.nextLine();

			// FOR each menu-item
			for (int j=0; j<k; j++) {
				String menuItem = sc.nextLine();

				if (menuItem.equals("pea soup")) {
					matchPeasoup = true;
				}

				if (menuItem.equals("pancakes")) {
					matchPancakes = true;
				}

				// IF both "pea soup" and "pancakes" are found in this restaurant
				if (matchPeasoup && matchPancakes) {
					// PRINT the name of the restaurant
					System.out.println(restaurantName);
					System.exit(0);
				}
			}
		}

		// PRINT the final message
		System.out.println("Anywhere is fine I guess");
	}
} 
