package OwnAnalysis;

public class Tracker {
	public int m;
	public Position[] allPositions;

	public Tracker(int m) {
		this.m = m;
		this.allPositions = new Position[m];

		for (int i=0; i<m; i++) {
			allPositions[i] = new Position(i);
		}
	}

	public void incrementPosition(int pos) {
		for (int i=0; i<m; i++) {
			if (allPositions[i].pos == pos) {
				allPositions[i].freq++;

				break;
			}
		}
	}

	public void print() {
		for (int i=0; i<m; i++) {
			System.out.println(allPositions[i].toString());
		}
	}
}