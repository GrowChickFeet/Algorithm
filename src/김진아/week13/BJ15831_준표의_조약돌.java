import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    static int N;
    static int B;
    static int W;
    static String road;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);
        W = Integer.parseInt(input[2]);
        road = reader.readLine();

        writer.write(Integer.toString(max()));

        writer.close();
        reader.close();
    }

    static int max() {
        int max = 0;

        int blackCount = 0;
        int whiteCount = 0;
        if (road.charAt(0) == 'B') blackCount++;
        else whiteCount++;

        int start = 0;
        int end = 1;

        while (start < N || end < N) {
            if (blackCount <= B && whiteCount >= W) max = Math.max(end - start, max);

            if (end == N || blackCount > B) {
                if (road.charAt(start) == 'B') blackCount--;
                else whiteCount--;
                start++;
            } else {
                if (road.charAt(end) == 'B') blackCount++;
                else whiteCount++;
                end++;
            }
        }

        return max;
    }

}
