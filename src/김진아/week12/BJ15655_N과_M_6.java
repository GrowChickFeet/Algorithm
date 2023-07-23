import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class Main {

    static StringBuilder builder;
    static int[] nums;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        nums = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(input[i]);
        Arrays.sort(nums);

        buildSequence(0, 0, new int[M]);

        writer.write(builder.toString());
        writer.close();
        reader.close();
    }

    static void buildSequence(int k, int n, int[] selected) {
        if (n == M) {
            for (int i = 0; i < n; i++) builder.append(nums[selected[i]]).append(" ");
            builder.append("\n");
            return;
        }

        for (int i = k; i < N - M + n + 1; i++) {
            selected[n] = i;
            buildSequence(i + 1, n + 1, selected);
        }
    }

}
