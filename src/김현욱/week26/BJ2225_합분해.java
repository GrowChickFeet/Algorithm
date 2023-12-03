package 김현욱.week26;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2225_합분해 {
    public static final int MOD = 1_000_000_000;
    public static int[][] dp;
    public static int N, K;

    public static int getDp(int n, int k) {
        if (k == K) {
            if (n == N) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dp[n][k] != -1) {
            return dp[n][k];
        }

        dp[n][k] = 0;

        for (int i = 0; i + n <= N; i++) {
            dp[n][k] = (dp[n][k] + getDp(n + i, k + 1)) % MOD;
        }

        return dp[n][k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];//dp[i][j] = 0~i까지 j개를 사용해서 만든 경우의 수
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        int result = getDp(0, 0);
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
