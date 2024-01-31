package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ21921_블로그 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int X = Integer.parseInt(input[1]);

        int[] hits = new int[N + 1];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) hits[i + 1] = hits[i] + Integer.parseInt(input[i]);

        int max = 0;
        int count = 0;
        for (int i = X; i <= N; i++) {
            int sum = hits[i] - hits[i - X];
            if (sum == max) count++;
            else if (sum > max) {
                max = sum;
                count = 1;
            }
        }

        writer.write(max > 0 ? new StringBuilder().append(max).append("\n").append(count).toString() : "SAD");

        writer.close();
        reader.close();
    }

}
