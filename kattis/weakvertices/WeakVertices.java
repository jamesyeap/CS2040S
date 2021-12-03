import java.io.*;
import java.util.*;

public class WeakVertices {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt();		
		sc.nextLine();

		while (V != -1) {
			int[][] adjMatrix = new int[V][V];

			for (int i=0; i<V; i++) {
				for (int j=0; j<V; j++) {
					adjMatrix[i][j] = sc.nextInt();							
				}				
				sc.nextLine();				
			}

			boolean[] tri = new boolean[V];

			for (int i=0; i<V; i++) {	// check all vertices to see if they are part of any triangles					

				for (int j=0; j<V; j++) {
					boolean found = false; 

					if (adjMatrix[i][j] == 1) {				//	if there is an edge from i to j

						for (int k=j+1; k<V; k++) {			
							if (adjMatrix[i][k] == 1 && adjMatrix[j][k] == 1) {		//	if there is an edge from i to k AND
																					//		if there is an edge from k to j,
																					//		then a triangle between i, j and k has been found
								tri[i] = true;
								tri[j] = true;
								tri[k] = true;

								found = true;									

								break;
							}
						}							
					}

					if (found == true) { break; }
				}
			}						

			for (int i=0; i<V; i++) {
				if (tri[i] == false) {
					System.out.print(Integer.toString(i) + " ");
				}
			}

			System.out.println();			
			V = sc.nextInt();
		}
	}
}
