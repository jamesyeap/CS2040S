import java.util.*;
import java.io.*;

public class Ladice {
	public static void main(String[] args) throws IOException {		
		Kattio io = new Kattio(System.in, System.out);

		int num_items = io.getInt();
		int num_drawers = io.getInt();

		UFDS drawers = new UFDS(num_drawers);

		int p1;
		int p2;
		for (int i=1; i<num_items+1; i++) { // 1-indexed					
			p1 = io.getInt();	
			p2 = io.getInt();

			if (drawers.insertItem(p1, p2) == true) {
				io.println("LADICA");
			} else {
				io.println("SMECE");
			}
		}

		io.close();
	}
}

class UFDS {
	int d[];
	int r[];	
	int c[];

	public UFDS(int num_drawers) {
		this.d = new int[num_drawers+1]; // 1-indexed
		for (int i=1; i<=num_drawers; i++) {
			d[i] = i;
		}

		this.r = new int[num_drawers+1]; // 1-indexed

		this.c = new int[num_drawers+1]; // 1-indexed
		Arrays.fill(this.c, 1); 		// all drawers have an initial capacity of 1
	}

	public boolean insertItem(int a, int b) {
		merge(a, b);

		if (getCapacity(a) > 0) {
			c[getSet(a)]--;

			return true;
		} else {
			return false;
		}
	}

	public void merge(int a, int b) { // merge 2 sets of drawers together		
		if (!isSameSet(a, b)) {
			if ( getRank(a) > getRank(b) ) { 			// if the set that "a" belongs to has a higher rank than the set that "b" is in
				c[getSet(a)] += c[getSet(b)];

				d[getSet(b)] = d[getSet(a)];			
				r[getSet(b)] = r[getSet(a)];
			} else if ( getRank(a) < getRank(b) ){ 		// if the set that "b" belongs to has a higher rank than the set that "a" is in
				c[getSet(b)] += c[getSet(a)];

				d[getSet(a)] = d[getSet(b)];
				r[getSet(a)] = r[getSet(b)];				
			} else {									// if both sets are of the same rank
				c[getSet(a)] += c[getSet(b)];

				d[getSet(b)] = d[getSet(a)]; 			// 		by convention, merge the right-set to the left-set if both sets are equal in rank
				r[getSet(a)]++;							//	increment the rank of the resulting-set by 1
				r[getSet(b)] = r[getSet(a)];
			}
		}
	}

	public boolean isSameSet(int a, int b) { // check if 2 drawers are from the same set
		return getSet(d[a]) == getSet(d[b]);
	}

	public int getSet(int a) { // get representative element of the set that the element at index a is in
		if (d[a] == a) { // if this is the representative element
			return a;
		} else {
			d[a] = getSet(d[a]); // path-compression

			return d[a]; 
		}
	}

	public int getRank(int a) {
		return r[getSet(a)];
	}

	public int getCapacity(int a) {
		return c[getSet(a)];
	}


}

// kattis io-reader

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


















