package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ11004_K번째_수 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[] nums = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(input[i]);

        Arrays.sort(nums);

        writer.write(Integer.toString(nums[K - 1]));

        writer.close();
        reader.close();
    }

}
