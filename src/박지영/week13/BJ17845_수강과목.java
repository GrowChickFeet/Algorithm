package 박지영.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17845_수강과목 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 최대 공부 시간
        int K = Integer.parseInt(st.nextToken());   // 과목 수

        int[][] sub = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            sub[i][0] = Integer.parseInt(st.nextToken());       // 중요도
            sub[i][1] = Integer.parseInt(st.nextToken());       // 공부 시간
        }

        long[] dp = new long[N+1];

        for (int i = 0; i < K; i++) {
            int weight = sub[i][0];
            int time = sub[i][1];
            for (int j = N; j >= time; j--) {
                dp[j] = Math.max(dp[j], dp[j-time] + weight);
            }
        }

        System.out.print(dp[N]);
    }
}
