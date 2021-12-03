import java.util.Comparator;

class AgeComparator implements Comparator<Person> {
	public int compare(Person p1, Person p2) {
		// CONVENTION: 
		return p1.getAge() - p2.getAge();
	}
}