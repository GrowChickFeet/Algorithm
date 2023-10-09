package 김진아.week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ10942_팰린드롬 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        String[] input = reader.readLine().split(" ");
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(input[i]);

        boolean[][] palindrome = new boolean[N][N];
        for (int i = 0; i < N; i++) palindrome[0][i] = true;
        for (int i = 0; i < N - 1; i++) palindrome[1][i] = nums[i] == nums[i + 1];
        for (int i = 2; i < N; i++) {
            for (int j = 0; j < N - i; j++) palindrome[i][j] = nums[j] == nums[i + j] && palindrome[i - 2][j + 1];
        }

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);

            writer.write(palindrome[end - start][start - 1] ? "1" : "0");
            writer.newLine();
        }

        writer.close();
        reader.close();
    }

}
