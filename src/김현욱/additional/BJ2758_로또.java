package 김현욱.additional;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2758_로또 {
    public static int result = 0;
    public static final int MAX_N = 10;
    public static final int MAX_M = 2000;
    public static long[][] dp = new long[MAX_N + 1][MAX_M + 1];

    public static long dfs(int count, int before) {
        if (before <= 0) {
            return 0;
        }
        if (dp[count][before] != -1) {
            return dp[count][before];
        }
        dp[count][before] = 0;
        dp[count][before] += dfs(count - 1, before / 2) + dfs(count, before - 1);
        return dp[count][before];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 1; i <= MAX_M; i++) {
            dp[1][i] = i;
        }
        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            sb.append(dfs(n, m)).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
