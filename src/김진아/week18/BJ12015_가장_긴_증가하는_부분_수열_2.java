package 김진아.week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ12015_가장_긴_증가하는_부분_수열_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int[] nums = new int[N];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(input[i]);

        int[] increase = new int[N];
        increase[0] = nums[0];
        int length = 1;

        for (int i = 1; i < N; i++) {
            if (nums[i] > increase[length - 1]) {
                increase[length++] = nums[i];
                continue;
            }

            int index = 0;
            int end = length;
            while (index < end) {
                int mid = (index + end) / 2;
                if (increase[mid] < nums[i]) index = mid+ 1;
                else end = mid;
            }

            increase[index] = nums[i];
        }

        writer.write(Integer.toString(length));

        writer.close();
        reader.close();
    }

}
