package 박지영.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dp를 이용해서 규칙을 찾아서 풀었습니다
 */
public class BJ11726_2Xn타일링 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n+1; i++) {
            dp[i] = (dp[i-1]+dp[i-2]) % 10007;
        }

        System.out.print(dp[n]);
    }
}
