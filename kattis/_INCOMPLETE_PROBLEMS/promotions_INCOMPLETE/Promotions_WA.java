
/* WRONG-ANSWER */

import java.util.*;
import java.io.*;

public class Promotions_WA {
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

		boolean[] visited = new boolean[nEmployees];

		int a = 0;
		int b = 0;
	
		while (a < A) {
			int promotionsCurrRound = 0; // number of promotions for this round
			List<Integer> employeesPromotedCurr = new ArrayList<>();

			for (int i=0; i<indeg.length; i++) {
				if (visited[i] == false && indeg[i] == 0) { // if this vertex has no edges going TO it -> means this employee the most duakia :O
					visited[i] = true;

					promotionsCurrRound++;
					employeesPromotedCurr.add(i);
				}
			}

			for (Integer employee : employeesPromotedCurr) {
				List<Integer> outperformed = adjList.get(employee);

				if (outperformed != null) {
					for (Integer nextEmployee : outperformed) {
						indeg[nextEmployee]--;
					}
				}	
			}		

			a += promotionsCurrRound;
			b += promotionsCurrRound;

			if (a == A) { // if first quota HIT
				break;
			} else if (a > A) { // if first quota EXCEEDED
				a -= promotionsCurrRound;

				if (b > B) { // if second quota also EXCEEDED
					b-= promotionsCurrRound;
				}

				break;
			}			
		}

		while (b < B) {
			int promotionsCurrRound = 0; // if number of promotions for this round
			List<Integer> employeesPromotedCurr = new ArrayList<>();

			for (int i=0; i<indeg.length; i++) {
				if (visited[i] == false && indeg[i] == 0) { // if this vertex has no edges going TO it -> means this employee the most duakia :O
					visited[i] = true;

					promotionsCurrRound++;
					employeesPromotedCurr.add(i);
				}
			}

			for (Integer employee : employeesPromotedCurr) {
				List<Integer> outperformed = adjList.get(employee);

				if (outperformed != null) {
					for (Integer nextEmployee : outperformed) {
						indeg[nextEmployee]--;
					}
				}	
			}		
			
			b += promotionsCurrRound;

			if (b == B) { // if second quota HIT
				break;
			} else if (b > B) { // if second quota EXCEEDED
				b -= promotionsCurrRound;

				break;
			}
		}


		io.println(a);
		io.println(b);
		io.println(nEmployees-b);
		io.close();		

		




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