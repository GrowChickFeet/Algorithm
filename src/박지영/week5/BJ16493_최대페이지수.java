package 박지영.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16493_최대페이지수 {
    static int N, M;    // N: 남을 기간, M: 책의 수
    
    static int[][] book;    // 일 수, 페이지 수
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        book = new int[M+1][2];
        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            book[i][0] = Integer.parseInt(st.nextToken());
            book[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[M+1][N+1];

        for (int i = 1; i < M+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (book[i][0] <= j){   // 날짜 계산
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j-book[i][0]] + book[i][1]);     // 페이지 수 계산
                } else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.print(dp[M][N]);
    }
}
