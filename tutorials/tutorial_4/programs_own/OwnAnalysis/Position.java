package OwnAnalysis;

public class Position {
	public int pos;
	public int freq;

	public Position(int pos) {
		this.pos = pos;
		this.freq = 0;
	}

	public void increment() {
		this.freq++;
	}

	@Override
	public String toString() {
		return String.format("[%d]: collisions: %d", this.pos, this.freq - 1);
	}
}