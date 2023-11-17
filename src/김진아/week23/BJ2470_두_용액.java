package 김진아.week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ2470_두_용액 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int[] liquids = new int[N];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) liquids[i] = Integer.parseInt(input[i]);

        Arrays.sort(liquids);

        int min = Integer.MAX_VALUE;
        int[] target = new int[2];
        int start = 0;
        int end = N - 1;

        while (start < end) {
            int sum = liquids[start] + liquids[end];
            int gap = Math.abs(sum);
            if (gap < min) {
                min = gap;
                target[0] = liquids[start];
                target[1] = liquids[end];
            }

            if (sum == 0) break;
            if (sum > 0) end--;
            else start++;
        }

        writer.write(new StringBuilder().append(target[0]).append(" ").append(target[1]).toString());

        writer.close();
        reader.close();
    }

}
