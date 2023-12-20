package 김현욱.additional;

import java.io.*;
import java.util.StringTokenizer;

public class BJ1915_가장_큰_정사각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] maze = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                maze[i + 1][j + 1] = (line[j] - '0');
            }
        }
        int maxLength = 0;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (maze[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        bw.write(Integer.toString(maxLength * maxLength));
        br.close();
        bw.close();
    }
}
