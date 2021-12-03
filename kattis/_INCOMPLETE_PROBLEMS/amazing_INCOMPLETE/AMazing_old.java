import java.util.*;
import java.io.*;

public class AMazing {	
	static Kattio io;	
	static Stack<Move> moves;
	static boolean solved;
	static boolean error;

	public static void main(String[] args) {
		io = new Kattio(System.in, System.out);
		
		moves = new Stack<Move>();
		moves.push(new Move("up", false));
		moves.push(new Move("down", false));
		moves.push(new Move("left", false));
		moves.push(new Move("right", false));

		solved = false; error = false;
		while (!moves.isEmpty() && solved == false && error == false) {
			Move currMove = moves.pop();

			if (currMove != null) {
				move(currMove);
			}
		}

		if (solved == false) {
			io.println("no way out");			
		}

		io.close();
	}

	static void move(Move m) {
		io.println(m.command); io.flush();		

		String response = io.getWord(); // get response from the server about the move		

		if (response.equals("wrong")) {	error=true; return; }				
		if (response.equals("solved")) { solved=true; return; }

		if (m.isRevert == false) {
			if (m.command.equals("up")) {												
				if (response.equals("ok")) {
					moves.push(new Move("down", true));

					moves.push(new Move("up", false)); 
					moves.push(new Move("left", false)); 
					moves.push(new Move("right", false));
				}
			}

			if (m.command.equals("down")) {						
				if (response.equals("ok")) {
					moves.push(new Move("up", true));

					moves.push(new Move("down", false)); 
					moves.push(new Move("left", false)); 
					moves.push(new Move("right", false));
				}
			}

			if (m.command.equals("left")) {			
				if (response.equals("ok")) {
					moves.push(new Move("right", true));

					moves.push(new Move("up", false)); 
					moves.push(new Move("left", false)); 
					moves.push(new Move("down", false));		
				}
			}

			if (m.command.equals("right")) {						
				if (response.equals("ok")) {
					moves.push(new Move("left", true));

					moves.push(new Move("up", false)); 
					moves.push(new Move("down", false)); 
					moves.push(new Move("right", false));
				}
			}
		}
	}
}

class Move {
	public String command;
	public boolean isRevert;

	public Move (String command, boolean isRevert) {
		this.command = command;
		this.isRevert = isRevert;
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
