import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        int[] nums = new int[N];

        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(input[i]);

        Arrays.sort(nums);

        int pre = nums[0];
        builder.append(nums[0]).append(" ");
        for (int i = 1; i < N; i++) {
            if (nums[i] == pre) continue;
            pre = nums[i];
            builder.append(nums[i]).append(" ");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
