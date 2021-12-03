/* use UFDS */

import java.util.*;
import java.io.*;

public class WheresMyInternet {
	static int[] p;
	static int[] r;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int N = io.getInt(); // number of houses
		int M = io.getInt(); // number of network cables already deployed

		/* ----- UFDS INITIALISATION ----------------------------------- */

		p = new int[N+1]; // 1-indexed parent array
		r = new int[N+1];	// 1-indexed rank array

		p[0] = -1; r[0] = -1;

		for (int i=1; i<=N; i++) {
			p[i] = i;
			r[i] = 1;
		}

		/* ------------------------------------------------------------ */

		Integer h1; Integer h2;
		for (int i=0; i<M; i++) {
			h1 = io.getInt(); h2=io.getInt();

			merge(h1, h2);
		}

		boolean allConnected = true;

		for (int i=1; i<=N; i++) {
			if (isSameSet(i, 1) == false) {
				allConnected = false;

				io.println(i);
			}
		}

		if (allConnected == true) {
			io.println("Connected");
		} 

		io.close();
	}

	static boolean isSameSet(int a, int b) {
		return getParent(a) == getParent(b);
	}

	static int getParent(int a) {
		if (p[a] == a) { return a; }

		p[a] = getParent(p[a]);

		return p[a];
	}

	static void merge(int a, int b) {
		if (!isSameSet(a, b)) {
			if (r[a] > r[b]) {
				r[getParent(b)] = r[getParent(a)];
				p[getParent(b)] = p[getParent(a)];

			} else if (r[a] < r[b]) {
				r[getParent(a)] = r[getParent(b)];
				p[getParent(a)] = p[getParent(b)];

			} else {
				r[getParent(a)]++;
				r[getParent(b)] = r[getParent(a)];

				p[getParent(b)] = p[getParent(a)];

			}
		}
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
