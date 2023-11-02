package 김현욱.week22;

import java.io.*;
import java.util.StringTokenizer;

public class BJ20544_공룡게임 {
    static StringTokenizer st;
    static final int MOD = 1_000_000_007;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        dp = new long[1002][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        dp[1][0] = 1;
        dp[1][1] = 0;
        dp[2][0] = 1;
        dp[3][0] = 2;
        dp[3][1] = 1;

        for (int i = 4; i <= n + 1; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + dp[i - 3][0]) % MOD;
            dp[i][1] = (dp[i - 1][1] + dp[i - 2][0] + dp[i - 2][1] * 2
                    + dp[i - 3][0] * 2 + dp[i - 3][1] * 3) % MOD;
        }
        bw.write(Long.toString(dp[n + 1][1]));
        br.close();
        bw.close();
    }
}
