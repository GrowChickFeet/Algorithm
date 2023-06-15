package 성민재.week9;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ24418_알고리즘수업_행렬경로문제1 {
    static int cnt1,cnt2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][N+1];

        for(int i = 1 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cnt1 = cnt2 = 0;
        matrix_path(map, N);
        matrix_dp(map,N);
        System.out.println(cnt1 + " " +cnt2);
    }

    private static int matrix_dp(int[][] map, int n) {
        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = map[i][j] + Math.max(dp[i-1][j], dp[i][j-1]);
                cnt2++;
            }
        }
        return dp[n][n];
    }

    private static int matrix_path(int[][] map, int N){
        return matrix_path_rec(map, N, N);
    }

    private static int matrix_path_rec(int[][] map, int i, int j){
        if(i == 0 ||j == 0){
            cnt1++;
            return 0;
        }
        else{
            return map[i][j] + Math.max(matrix_path_rec(map, i-1, j),
                    matrix_path_rec(map, i,j-1));
        }
    }

    static class Node{
        int r, c, cnt;

        public Node(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}
