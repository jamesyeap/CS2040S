import java.util.Scanner;

public class ExactlyElectrical {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();
		sc.nextLine();

		int c = sc.nextInt();
		int d = sc.nextInt();
		sc.nextLine();

		int charge = sc.nextInt();

		int distance = Math.abs(a-c) + Math.abs(b-d);

		if (distance <= charge) {
			if (Math.abs(distance - charge) % 2 == 0) {
				System.out.println("Y");
			} else {
				System.out.println("N");	
			}
		} else {
			System.out.println("N");
		}
	}
}
