package 박지영.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 재귀와 다이나믹프로그래밍 비교
 */
public class BJ24418_알고리즘수업_행렬경로문제1 {
    static int countA, countB;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        matrixPath(map, N);
        dinamicMP(map, N);
        System.out.println(countA+" "+countB);
    }

    static int matrixPath(int[][] m, int n) {
        return matrixPathRec(m, n, n);
    }

    private static int matrixPathRec(int[][] m, int i, int j) {
        if (i==0 || j==0) {
            countA++;
            return 0;
        }
        else return (m[i][j] + Math.max(matrixPathRec(m, i-1, j), matrixPathRec(m, i, j-1)));
    }

    static void dinamicMP(int[][] m, int n) {
        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = m[i][j] + Math.max(dp[i-1][j], dp[i][j-1]);
                countB++;
            }
        }
    }
}
