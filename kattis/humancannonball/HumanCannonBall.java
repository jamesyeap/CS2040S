import java.util.*;
import java.io.*;
import java.awt.geom.Point2D;

public class HumanCannonBall {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		double xStart = io.getDouble(); 
		double yStart = io.getDouble();
		Point2D.Double startPos = new Point2D.Double(xStart, yStart);		

		double xEnd = io.getDouble(); 
		double yEnd = io.getDouble();
		Point2D.Double endPos = new Point2D.Double(xEnd, yEnd);		

		int nCannons = io.getInt();

		Point2D.Double[] positions = new Point2D.Double[nCannons+2];
		positions[0] = startPos;
		positions[positions.length-1] = endPos;

		for (int i=1; i<nCannons+1; i++) {
			double xCannon = io.getDouble();
			double yCannon = io.getDouble();			
			Point2D.Double cannonPos = new Point2D.Double(xCannon, yCannon);

			positions[i] = cannonPos;			
		}

		double[][] travelTime = new double[nCannons+2][nCannons+2];		
		for (int i=0; i<nCannons+2; i++) {			
			for (int j=0; j<nCannons+2; j++) {
				if (i == j) {
					travelTime[i][j] = 0;
				} else {
					travelTime[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}

		for (int i=1; i<nCannons+2; i++) {
			Point2D.Double to = positions[i];
			double distance = startPos.distance(to);

			double timeTakenByWalking = (distance / 5.0); // in seconds
			travelTime[0][i] = timeTakenByWalking;
		}

		for (int i=1; i<nCannons+1; i++) {			
			Point2D.Double from = positions[i];
			Point2D.Double to;			

			for (int j=i+1; j<nCannons+2; j++) {				
				to = positions[j];

				double distance = from.distance(to);
				double timeTakenByWalking = (distance / 5); // in seconds				
				
				double excessDistance = Math.abs(distance - 50); // extra distance that you'll have to walk, if any
				double timeTakenUsingCannon = 2 + (excessDistance / 5);							

				travelTime[i][j] = Math.min(timeTakenUsingCannon, timeTakenByWalking);
				travelTime[j][i] = travelTime[i][j]; // as the distance from u->v is the same as distance from v->u				
			}
		}

		for (int k=0; k < nCannons+2; k++) {
			for (int i=0; i<nCannons+2; i++) {
				for (int j=0; j<nCannons+2; j++) {					
					double alt = travelTime[i][k] + travelTime[k][j];					
					travelTime[i][j] = Math.min(travelTime[i][j], alt);
				}
			}
		}

		io.println(travelTime[0][travelTime[0].length-1]);
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