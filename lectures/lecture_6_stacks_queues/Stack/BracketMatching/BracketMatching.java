public class BracketMatching {
	public static void main(String[] args) {
		String testCorrect = "{ a - (b + f [ 4 ] ) }";
		String testWrong = "{ a + (b + c) )";

		String[] split = testCorrect.split("");

		for (int i=0; i<split.length; i++) {
			if (split[i].equals("{") 
				|| split[i].equals("(") 
				|| split[i].equals("[")) 
			{
				System.out.println("Hello");
			}
			System.out.println(split[i]);
		}
	}
}