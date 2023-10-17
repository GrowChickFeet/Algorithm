package 김현욱.week19;

import java.io.*;

public class BJ2133_타일_채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[31];
        dp[0] = 1;
        dp[2] = 3;
        dp[4] = 11;

        for(int i=6;i<=n;i+=2){
            dp[i] = dp[i-2] * 3;
            for(int j=i-4;j>=0;j-=2){
                dp[i] += dp[j] * 2;
            }
        }
        bw.write(Integer.toString(dp[n]));
        br.close();
        bw.close();
    }
}
