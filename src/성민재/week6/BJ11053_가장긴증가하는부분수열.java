package 성민재.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11053_가장긴증가하는부분수열 {
    static StringTokenizer st = null;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] input = new int[n];
        int[] dp = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }
        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(input[i] > input[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        System.out.println(ans);
    }
    //상자문제와 동일하다. 다만 테케에서 차이가 있었는데 여기서는 문자열이 수열의 길이가 딱 1개만 주어졌을 때 ans를 0으로 설정해 놓으면 크기는 1인데
    //for문안으로 들어가서 ans를 갱신시켜주지 못하기 때문에 틀리게 된다.
}
