// can just use java.util.*; imported individually here for clarity
import java.util.Collections; 
import java.util.Arrays;
import java.util.List;

public class CollectionsSort {
	public static void main(String args[]) {
		List<String> lst = Arrays.asList(args);
		Collections.sort(lst);
		System.out.println(lst);
	}
}