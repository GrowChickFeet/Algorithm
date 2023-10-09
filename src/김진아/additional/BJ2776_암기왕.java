package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ2776_암기왕 {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(reader.readLine());
            nums = new int[N];

            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) nums[j] = Integer.parseInt(input[j]);

            Arrays.sort(nums);

            int M = Integer.parseInt(reader.readLine());
            input = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) builder.append(exist(Integer.parseInt(input[j]))).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int exist(int num) {
        int start = 0;
        int end = N - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == num) return 1;
            if (nums[mid] < num) start = mid + 1;
            else end = mid - 1;
        }
        return 0;
    }

}
