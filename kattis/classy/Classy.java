import java.util.*;

public class Classy {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int nTestCases = Integer.parseInt(sc.nextLine());

		for (int i=0; i<nTestCases; i++) {
			int nPeople = Integer.parseInt(sc.nextLine());
			int nLevels = 1;
			
			// PriorityQueue ensures that the list is always sorted in alphabetical order
			PriorityQueue<Map.Entry<String, ArrayList<Integer>>> peopleList = new PriorityQueue<Map.Entry<String, ArrayList<Integer>>>(nPeople, new SortByName());

			for (int j=0; j<nPeople; j++) {
				String[] currPerson = sc.nextLine().split("-");

				if (currPerson.length > nLevels) {
					nLevels = currPerson.length;					
				}

				// get the name of the person; to use as key
				int nameEndIndex = currPerson[0].indexOf(":");
				String name = currPerson[0].substring(0, nameEndIndex);				

				ArrayList<Integer> classList = new ArrayList<Integer>(nLevels);

				for (int k=currPerson.length-1; k>=0; k--) {
					if (currPerson[k].contains("upper")) {
						classList.add(2);
					} else if (currPerson[k].contains("middle")) {
						classList.add(1);
					} else { // currPerson[k].contains("lower")
						classList.add(0);
					}
				}
				
				peopleList.add(new AbstractMap.SimpleEntry<String, ArrayList<Integer>>(name, classList));		
			}

			final int maxLevel = nLevels; // added line to address error "... must be final or effectively final..."
			peopleList.forEach((e) -> {
				while (e.getValue().size() < maxLevel) {
					e.getValue().add(1); // pad with "middle"
				}				
			});

			// after sorting by alphabetical order, sort by class
			LinkedList<Map.Entry<String, ArrayList<Integer>>> sorted = new LinkedList<Map.Entry<String, ArrayList<Integer>>>();
			while (peopleList.size() > 0) {
				Map.Entry<String, ArrayList<Integer>> currEntry = peopleList.poll();			

				sorted.add(currEntry);
			}

			Collections.sort(sorted, new SortByClass());

			for (int j=0; j<nPeople; j++) {
				System.out.println(sorted.poll().getKey());
			}

			System.out.println("==============================");
		}
	}

	static class SortByName implements Comparator<Map.Entry<String, ArrayList<Integer>>> {	
		public int compare(Map.Entry<String, ArrayList<Integer>> a, Map.Entry<String, ArrayList<Integer>> b) {
			return a.getKey().compareTo(b.getKey());
		}
	}

	static class SortByClass implements Comparator<Map.Entry<String, ArrayList<Integer>>> {
		public int compare(Map.Entry<String, ArrayList<Integer>> a, Map.Entry<String, ArrayList<Integer>> b) {
			ArrayList<Integer> firstClassList = a.getValue();			
			ArrayList<Integer> secondClassList = b.getValue();

			for (int i=0; i<firstClassList.size(); i++) {
				if (firstClassList.get(i) > secondClassList.get(i)) {
					return -1;
				} 

				if (firstClassList.get(i) < secondClassList.get(i)) {
					return 1;
				}
			}

			return 0;
		}
	}
}
