package 김현욱.week9;

import java.io.*;
import java.util.StringTokenizer;

public class BJ24418_알고리즘_수업_행렬_경로_문제_1 {
    static int n;
    static int[][] maze;

    static int result1 = 0;
    static int result2 = 0;

    public static int matrix_path_rec(int x, int y) {
        if (x == 0 || y == 0) {
            result1++;
            return 0;
        } else {
            return (maze[x][y] + Math.max(matrix_path_rec(x - 1, y), matrix_path_rec(x, y - 1)));
        }
    }

    public static int matrix_path_dp() {
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = maze[i][j] + Math.max(dp[i-1][j],dp[i][j-1]);
                result2++;
            }
        }
        return dp[n][n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        maze = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        matrix_path_rec(n, n);
        matrix_path_dp();

        StringBuilder sb = new StringBuilder();
        sb.append(result1).append(' ').append(result2);
        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}
