package 김현욱.week8;

import java.io.*;

public class BJ11726_2xn타일링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            dp[i] =(dp[i-1]+dp[i-2])%10007;
        }

        bw.write(Integer.toString(dp[n]));

        bw.close();
        br.close();
    }
}
