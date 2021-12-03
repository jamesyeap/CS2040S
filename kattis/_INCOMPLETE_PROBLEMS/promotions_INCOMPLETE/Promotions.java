/* ALSO WRONG ANSWER :( */

import java.util.*;
import java.io.*;

public class Promotions {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int A = io.getInt(); int B = io.getInt(); // get interval endpoints [A, B]

		int nEmployees = io.getInt(); // number of employees
		int nRules = io.getInt(); // number of precedence-rules

		HashMap<Integer, List<Integer>> adjList = new HashMap<>(nEmployees);
		int[] indeg = new int[nEmployees];

		for (int i=0; i<nRules; i++) {
			int x = io.getInt(); 
			int y = io.getInt();

			if (!adjList.containsKey(x)) {
				List<Integer> outperforms = new ArrayList<>();
				adjList.put(x, outperforms);
			}

			adjList.get(x).add(y);
			indeg[y]++;
		}

		List<Employee> employees = new ArrayList<>(nEmployees);
		HashMap<Integer, List<Integer>> promotionRounds = new HashMap<>();

		for (int i=0; i<indeg.length; i++) {
			employees.add(new Employee(i, indeg[i]));
		}

		Collections.sort(employees);

		for (Employee employee : employees) {
			if (!promotionRounds.containsKey(employee.indeg)) {
				List<Integer> employeesPromoted = new ArrayList<>();				
				promotionRounds.put(employee.indeg, employeesPromoted);				
			}

			promotionRounds.get(employee.indeg).add(employee.id);
		}

		int a = 0;
		for (Map.Entry<Integer, List<Integer>> round : promotionRounds.entrySet()) {
			if (a + round.getValue().size() == A) {
				a += round.getValue().size();
				break;
			} else if (a + round.getValue().size() > A) {
				break;
			}

			a += round.getValue().size();
		}

		int b = 0;

		Set<Integer> roundsConsidered = new HashSet<>(promotionRounds.size());

		for (Map.Entry<Integer, List<Integer>> round : promotionRounds.entrySet()) {
			roundsConsidered.add(round.getKey());

			if (b + round.getValue().size() == B) {
				b += round.getValue().size();			
				break;
			} else if (b + round.getValue().size() > B) {
				break;
			}

			b += round.getValue().size();
		}

		int c = 0;
		for (Map.Entry<Integer, List<Integer>> round : promotionRounds.entrySet()) {
			if (!roundsConsidered.contains(round.getKey())) {
				c += round.getValue().size();
			}
		}

		io.println(a);
		io.println(b);
		io.println(c);
	
		io.close();
	}
}

class Employee implements Comparable<Employee> {
	int id;
	int indeg; 

	public Employee(int id, int indeg) {
		this.id = id;
		this.indeg = indeg;
	}

	@Override
	public int compareTo(Employee other) {
		return this.indeg - other.indeg;
	}

	@Override
	public String toString() {
		return String.format("[%d] %d", id, indeg);
	}
}


class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
	super(new BufferedOutputStream(System.out));
	r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
	super(new BufferedOutputStream(o));
	r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
	return peekToken() != null;
    }

    public int getInt() {
	return Integer.parseInt(nextToken());
    }

    public double getDouble() { 
	return Double.parseDouble(nextToken());
    }

    public long getLong() {
	return Long.parseLong(nextToken());
    }

    public String getWord() {
	return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
	if (token == null) 
	    try {
		while (st == null || !st.hasMoreTokens()) {
		    line = r.readLine();
		    if (line == null) return null;
		    st = new StringTokenizer(line);
		}
		token = st.nextToken();
	    } catch (IOException e) { }
	return token;
    }

    private String nextToken() {
	String ans = peekToken();
	token = null;
	return ans;
    }
}