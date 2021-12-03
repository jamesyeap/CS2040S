import java.util.*;
import java.io.*;

public class Hyacinth {
    static HashMap<Integer, List<Integer>> nodeFreq;
    static HashMap<Integer, List<Integer>> adjList;
    static boolean[] visited;
    static List<Integer> topoSorted;
    static UFDS networks;
    static Deque<Integer> availFreqs;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int N = io.getInt(); // number of nodes

        /* ------ INITIALISATION -------------------------------------------- */        
        nodeFreq = new HashMap<>(N);
        adjList = new HashMap<>(N);
        visited = new boolean[N+1];
        networks = new UFDS(N);
        
        for (int i=1; i<=N; i++) {            
            List<Integer> freqs = new ArrayList<>(2);            
            nodeFreq.put(i, freqs);

            List<Integer> adjV = new ArrayList<>();
            adjList.put(i, adjV);
        }

        Integer n1; Integer n2;
        for (int i=0; i<N-1; i++) { // populate adjacency-list
            n1 = io.getInt(); n2 = io.getInt();

            adjList.get(n1).add(n2);
            adjList.get(n2).add(n1);
        }
        /* --------------------------------------------------------------- */

        /* ------ TOPOSORT TO GET THE ROOT-NODE -------------------------- */
        topoSorted = new ArrayList<>(N);

        for (int i=1; i<=N; i++) {
            dfsTopoSort(i);
        }
        Collections.reverse(topoSorted);

        // io.println(topoSorted);
        // io.close();
        /* -------------------------------------------------------------- */

        Arrays.fill(visited, false); // reset the visited-array

        availFreqs = new ArrayDeque<>(N*2);
        for (int i=1; i<=N*2; i++) {
            availFreqs.push(i);
        }

        for (Integer i : topoSorted) {
            if (visited[i] == false) {
                visited[i] = true;

                List<Integer> adjV = adjList.get(i);

                if (adjV != null) {                
                    for (Integer immediateChildren : adjV) {
                        if (nodeFreq.get(i).size() < 2) {
                            int freq = availFreqs.pop();
                            nodeFreq.get(i).add(freq); // assign a free-frequency to this node, and its next immediate-child

                            dfs(i, freq);
                        }
                    }
                } else {
                    if (nodeFreq.get(i).size() < 2) {
                        int freq = availFreqs.pop();

                        nodeFreq.get(i).add(freq); // assign a free-frequency to this node, and its next immediate-child                    
                    }                            
                }
            }
        }

        for (Map.Entry<Integer, List<Integer>> entry : nodeFreq.entrySet()) {
            List<Integer> assignedFreqList = entry.getValue();

            if (assignedFreqList != null) { // which it shouldn't even be so a little redundant but okay,
                String result = "";

                for (Integer freq : assignedFreqList) {
                    result += Integer.toString(freq) + " ";
                }

                io.println(result);
            }
        }

        io.close();        
    }

    static void dfs(Integer t, Integer pFreq) {
        if (visited[t] == false) {
            visited[t] = true;

            nodeFreq.get(t).add(pFreq);

            List<Integer> adjV = adjList.get(t);

            if (adjV != null) {
                Integer freq;

                if (nodeFreq.get(t).size() < 2) { // if still lesser than 2,
                    freq = availFreqs.pop();

                    nodeFreq.get(t).add(freq);
                } else {
                    freq = pFreq;
                }

                for (int i=0; i<adjV.size(); i++) {
                    dfs(i, freq);
                } 
            } else {
                if (nodeFreq.get(t).size() < 2) { // if still lesser than 2,
                    int freq = availFreqs.pop();

                    nodeFreq.get(t).add(freq);
                }
            }
        }
    }

    static void dfsTopoSort(Integer t) {
        if (visited[t] == false) {
            visited[t] = true;
        
            List<Integer> adjV = adjList.get(t);

            if (adjV != null) {
                for (Integer v : adjV) {
                    dfsTopoSort(v);

                    networks.merge(t, v);
                }
            }

            topoSorted.add(t);
        }
    }

}

class UFDS {
    int[] p;
    int nDisjointSets;

    public UFDS(int nElems) {
        p = new int[nElems+1]; // 1-indexed
        nDisjointSets = nElems;

        for (int i=1; i<=nElems; i++) {
            p[i] = i;
        }
    }

    public boolean isSameSet(int a, int b) {
        return getParent(a) == getParent(b);
    }

    public int getParent(int a) {
        if (p[a] == a) { return a; }

        p[a] = getParent(p[a]);

        return p[a];
    }

    public void merge(int a, int b) {
        if (!isSameSet(a, b)) {
            p[getParent(b)] = getParent(a);

            nDisjointSets--;
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
