
import java.util.*;

public class Workstations {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String buffer = sc.nextLine();
		String[] split = buffer.split(" ");

		int n = Integer.parseInt(split[0]);
		int m = Integer.parseInt(split[1]);

		PriorityQueue<Researcher> sortedResearchers = new PriorityQueue<>(n, new Comparator<>() {
			@Override
			public int compare(Researcher r1, Researcher r2) {
				return r1.arrivesAt - r2.arrivesAt; // sorted in ascending-order (lowest-to-highest)
			}
		});

		for (int i=0; i<n; i++) {
			buffer = sc.nextLine();
			split = buffer.split(" ");

			sortedResearchers.offer(new Researcher(Integer.parseInt(split[0]), Integer.parseInt(split[1])));									
		}

		// init 2 min-heaps
		PriorityQueue<Integer> inUse = new PriorityQueue<>();
		PriorityQueue<Integer> unusedUnlocked = new PriorityQueue<>();

		// init result
		int savedUnlocks = 0;

		for (int i=0; i<n; i++) {			
			Researcher r = sortedResearchers.poll();

			while (!inUse.isEmpty()) {
				if (inUse.peek() > r.arrivesAt) {
					break; // don't need to shift the rest yet as these are still in-use
				}				
				unusedUnlocked.offer(inUse.poll() + m);				
			}			

			while (!unusedUnlocked.isEmpty()) {
				if (unusedUnlocked.peek() >= r.arrivesAt) {
					break;	// don't need to remove these yet as these are still unlocked
				}
				unusedUnlocked.poll();				
			}			

			if (!unusedUnlocked.isEmpty()) {
				unusedUnlocked.poll();
				savedUnlocks++; // now Penelope has more time to scroll insta :)

				inUse.offer(r.endTime);
			} else {
				inUse.offer(r.endTime);
			}

			// System.out.println(inUse);
			// System.out.println(unusedUnlocked);
		}

		System.out.println(savedUnlocks);
	}
}

class Researcher {
	public int arrivesAt;
	public int endTime;

	public Researcher(int arrivesAt, int usageTime) {
		this.arrivesAt = arrivesAt;
		this.endTime = arrivesAt+usageTime;
	}

	@Override
	public String toString() {
		return String.format("arrivalTime: %d", this.arrivesAt);
	}
}
