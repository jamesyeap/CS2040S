/* PSEUDO-CODE

pool <- init a TreeMap containing QuestSet; sorted from highest to lowest energy-requirement, where:
			QuestSet contains all Quests with the same energy-requirement

			internally, QuestSet stores Quests in a max-heap; with the order determined by the gold-reward of each Quest


for each command:
	if "add" command
		if pool does not have a QuestSet that contains Quests with the same energy-requirement:
			pool.put(energy, new QuestSet())
		
		add the new Quest to the QuestSet:
			pool.get(energy)
				.add(reward)

	if "query" command

		if pool is empty:
			return 0

		x <- keep a count of energy left
		r <- keep a count of rewards received

		while x > 0:
			if pool.get(x) != null:
				currQuests <- pool.get(x)
			else:
				currQuests <- pool.floorEntry(x)

			if currQuests != null:						// if there exist Quest(s) that can be done with remaining energy
				for each Quest in currQuests:
					if x - energy >= 0:
						r += currQuests.remove(); 		// do the Quest with the highest gold-reward

						if currQuests.isEmpty():		//	if there are no more Quests with such energy-requirement, remove the category from the pool
							pool.remove(currQuests.getKey());
					else:
						break;
			else:										// if not, return the rewards received so far
				return r;
*/

import java.util.*;

public class KattisQuest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long n = Long.parseLong(sc.nextLine());

		TreeMap<Integer,PriorityQueue<Integer>> m = new TreeMap<Integer,PriorityQueue<Integer>>();		

		String[] b;
		for (long i=0; i<n; i++) {
			b = sc.nextLine().split(" ");

			if (b[0].equals("add")) {			/* add command */
				int e = Integer.parseInt(b[1]);
				int r = Integer.parseInt(b[2]);

				if (!m.containsKey(e)) {
					m.put(e, (new PriorityQueue<Integer>(new Comparator<Integer>() { 
						@Override				
						public int compare(Integer a, Integer b) {
							return b-a;
						}
					})));
				}
					
				m.get(e).offer(r);
			} else {							/* query command */

				int x = Integer.parseInt(b[1]); 
				long r = 0;		

				if (!m.isEmpty()) {					
					Map.Entry<Integer,PriorityQueue<Integer>> qs_entry;					

					while (x > 0) {						
						qs_entry = m.floorEntry(x);

						if (qs_entry == null) {
							break;
						} else {
							PriorityQueue<Integer> qs = qs_entry.getValue();

							while (!qs.isEmpty()) {
								if (x - qs_entry.getKey() >= 0) {
									x -= qs_entry.getKey();
									r += qs.poll();									
								} else {
									break;
								}
							}

							if (qs.isEmpty()) {
								m.remove(qs_entry.getKey());
							} else {
								m.put(qs_entry.getKey(), qs);
							}
						}					
					}
				}

				System.out.println(r);				
			}
		}
	}
}
