import java.util.*;

public class Conformity {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nFrosh = Integer.parseInt(sc.nextLine());

		HashMap<HashSet<String>,Integer> record = new HashMap<HashSet<String>,Integer>();
		int highestCount = 0;

		for (int i=0; i<nFrosh; i++) {
			String buffer = sc.nextLine();
			String[] split = buffer.split(" ");
			HashSet<String> courses = new HashSet<>(5);

			for (int j=0; j<split.length; j++) {
				courses.add(split[j]);
			}

			int newCount;
			if (record.containsKey(courses)) { // O(1) - check existence of key
				newCount = record.get(courses) + 1;
			} else {
				newCount = 1;
			}
			record.put(courses, newCount);

			if (newCount > highestCount) {
				highestCount = newCount;
			}
		}

		int nStudents = 0; // count the number of students taking the most-popular course(s)
		List<Integer> allCounts = new ArrayList<>(record.values());

		for (int i=0; i<allCounts.size(); i++) {
			if (allCounts.get(i) == highestCount) {
				nStudents += highestCount; 
			}
		}

		System.out.println(nStudents);
	}
}

