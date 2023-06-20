package 김현욱.week11;

import java.io.*;

public class BJ15989_1_2_3_더하기4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[10001][4];

        dp[1][1] = 1;

        dp[2][1] = dp[1][1];
        dp[2][2] = 1;

        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;


        for(int i=4;i<=10000;i++){
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1]+dp[i-2][2];
            dp[i][3] = dp[i-3][1]+dp[i-3][2]+dp[i-3][3];
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            int value = Integer.parseInt(br.readLine());
            sb.append(dp[value][1]+dp[value][2]+dp[value][3]).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
