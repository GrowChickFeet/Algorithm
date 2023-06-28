package 남현실.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
2*N을 1*2, 2*1타일로 채우기
 */
public class BJ11726_2xn_타일링 {
    static final int MOD = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+2];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%MOD;
        }

        System.out.println(dp[N]);
    }
}
