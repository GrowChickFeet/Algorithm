package 김진아.week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ2559_수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[] temperature = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) temperature[i] = Integer.parseInt(input[i]);

        int sum = 0;
        for (int i = 0; i < K; i++) sum += temperature[i];

        int max = sum;
        for (int i = 1; i <= N - K; i++) {
            sum += temperature[i + K - 1] - temperature[i - 1];
            max = Math.max(sum, max);
        }

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

}
