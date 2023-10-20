package 김진아.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ15988_1_2_3_더하기_3 {

    final static int MOD = 1000000009;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        int[] nums = new int[T];
        int max = 0;
        for (int i = 0; i < T; i++) {
            nums[i] = Integer.parseInt(reader.readLine());
            max = Math.max(nums[i], max);
        }

        int[] count = new int[max + 3];
        for (int i = 1; i <= 3; i++) {
            count[i] = 1;
            for (int j = i - 1; j > 0; j--) count[i] += count[j];
        }
        for (int i = 4; i <= max; i++) {
            for (int j = 1; j <= 3; j++) count[i] = (count[i] + count[i - j]) % MOD;
        }

        for (int num : nums) builder.append(count[num]).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
