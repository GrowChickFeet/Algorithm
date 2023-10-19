package 김현욱.week20;

import java.io.*;
import java.util.StringTokenizer;

public class BJ15988_1_2_3더하기3 {
    static StringTokenizer st;
    static int MOD = 1_000_000_009;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        long dp[] = new long[1_000_001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i <= 1_000_000; i++)
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1_000_000_009;

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<t;i++){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
