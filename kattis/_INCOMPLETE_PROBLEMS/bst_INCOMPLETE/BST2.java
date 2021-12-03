import java.util.*;
import java.io.*;

public class BST2 {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int n = io.getInt(); // total number of elements = n; range of elements = [1, n]

		TreeMap<Integer,Integer> tm = new TreeMap<>();

		Map.Entry<Integer,Integer> a;
		Map.Entry<Integer,Integer> b;
		Map.Entry<Integer,Integer> z;

		int c = 0;

		for (int i=0; i<n; i++) {
			int k = io.getInt();

			a = tm.higherEntry(k); // a > k
			b = tm.lowerEntry(k);   // b < k

			if (a == null && b == null) { // means no elements inside
				tm.put(k, 0);

				// io.println(String.format("%d placed at root\n", k));				
			} else if (a == null && b != null) { // means k is the largest-element so far encountered
				tm.put(k, b.getValue()+1);
				c += (b.getValue()+1);

				// io.println(String.format("no elements greater than %d were found!\n", k));				
			} else if (b == null && a != null) { // means k is the smallest-element so far encountered
				tm.put(k, a.getValue()+1);
				c += (a.getValue()+1);

				// io.println(String.format("no elements smaller than %d were found!\n", k));								
			} else {
				z = b.getValue() > a.getValue() ? b : a;
				tm.put(k, z.getValue()+1);
				c += (z.getValue()+1);

				// io.println(String.format("%d | %d %d\n", k, a.getKey(), b.getKey()));
				// io.println(String.format("depth of %d from element %d was chosen\n", z.getValue(), z.getKey()));								
			}

			io.println(c);
		}

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
