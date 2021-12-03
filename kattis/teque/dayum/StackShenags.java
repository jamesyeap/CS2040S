import java.util.*;
import java.io.*;

public class StackShenags {
    static StringBuilder result = new StringBuilder();

    // static int[] front = new int[1_000_000];
    // static int[] back = new int[1_000_000];
    static int[] front = new int[20];
    static int[] back = new int[20];

    // static int frontHead = 499_999;
    // static int backHead = 499_999;
    static int frontHead = 9;
    static int backHead = 9;

    static int frontSize = 0;
    static int backSize = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");

            if (line[0].equals("get")) {
                int index = Integer.parseInt(line[1]);

                if (index >= frontSize) result(back[backHead + index - frontSize]);
                else result(front[frontHead + index]);

                continue;
            }

            if (frontSize == backSize) {
                if (line[0].equals("push_front")) {
                    frontHead--;
                    front[frontHead] = Integer.parseInt(line[1]);
                    frontSize++;
                } else if (line[0].equals("push_back")) {
                    back[backHead + backSize] = Integer.parseInt(line[1]);
                    
                    front[frontHead + frontSize] = back[backHead];
                    frontSize++;
                    backHead++;
                } else if (line[0].equals("push_middle")) {
                    front[frontHead + frontSize] = Integer.parseInt(line[1]);
                    frontSize++;
                }

                // System.out.println(Arrays.toString(front) + Arrays.toString(back));
            } else {
                if (line[0].equals("push_front")) {
                    frontHead--;
                    front[frontHead] = Integer.parseInt(line[1]);

                    backHead--;
                    back[backHead] = front[frontHead + frontSize];
                    backSize++;
                } else if (line[0].equals("push_back")) {
                    back[backHead + backSize] = Integer.parseInt(line[1]);
                    backSize++;
                } else if (line[0].equals("push_middle")) {
                    backHead--;
                    back[backHead] = Integer.parseInt(line[1]);
                    backSize++;
                }

                // System.out.println(Arrays.toString(front) + Arrays.toString(back));
            }
        }

        System.out.print(result);
    }

    private static void result(int value) {
        result.append(value).append("\n");
    }
}