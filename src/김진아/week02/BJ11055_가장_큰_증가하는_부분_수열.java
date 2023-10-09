package 김진아.week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ11055_가장_큰_증가하는_부분_수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        int[] nums = new int[N + 1];
        String[] input = reader.readLine().split(" ");
        for (int i = 1; i <= N; i++) nums[i] = Integer.parseInt(input[i - 1]);

        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] >= nums[i]) continue;
                sum[i] = Math.max(sum[j] + nums[i], sum[i]);
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) max = Math.max(sum[i], max);

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

}
