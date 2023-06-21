package 남현실.week11;

/*
https://www.acmicpc.net/problem/4811
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

 */
public class BJ4811_알약 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int W = 30;
        int H = 30;
        long dp[][] = new long[W+1][H+1];
        for (int w = 0; w < W+1; w++) {
            for (int h = 0; h < H+1; h++) {
                if(w < h){
                    continue;
                }
                if(h == 0) {
                    dp[w][h] = 1;
                    continue;
                }
                dp[w][h] = dp[w][h-1] + dp[w-1][h];
            }
        }

        StringBuilder result = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N > 0) {
            result.append(dp[N][N]).append("\n");
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(result.toString());
    }
}
