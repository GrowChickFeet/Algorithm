package 김진아.week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ2491_수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        int[] nums = new int[N];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(input[i]);

        int max = 1;

        int increase = 1;
        int decrease = 1;
        for (int i = 1; i < N; i++) {
            if (nums[i] > nums[i - 1]) {
                increase++;
                decrease = 1;
            } else if (nums[i] < nums[i - 1]) {
                increase = 1;
                decrease++;
            } else {
                increase++;
                decrease++;
            }

            max = Math.max(Math.max(increase, decrease), max);
        }

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

}
