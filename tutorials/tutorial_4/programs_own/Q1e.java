import OwnAnalysis.*;

public class Q1e {
	public static double h(int key) {
		return Math.floor(key * Math.random()) % 101;
	}

	public static void main(String[] args) {
		int m = 100;

		Tracker table = new Tracker(m);

		for (int i=0; i<1000; i++) {
			int hashOutput = (int)h(i);

			table.incrementPosition(hashOutput);
		}

		table.print();
	}
}