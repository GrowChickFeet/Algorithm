package 남현실.week4;

/*
https://www.acmicpc.net/problem/16395
 */

/*
[입력]
- n: 행
- k: n번 째 행의 k번째 수
- 1 ≤ k ≤ n ≤ 30

[정보]
- N번쨰 행 N개의 개수
- 첫번째 행: 1
- 두 번째 행부터
    - 각 행 양끝 값: 1
    - 나머지 값: 바로 위 행의 인접한 두 수의 합

[출력]
- n번쨰 항에 있는 k번째 수
- C(n-1,k-1)

[풀이]
- 재귀를 통해 구한다
    - k[1, n-1] => 1
    - n[k] => prevN[k-1]+prevN[k]
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ16395_파스칼의_삼각형 {
    static int N;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);
        dp = new long[31][31];
        //dp[n][k] = v
        for (int i = 0; i <= 30 ; i++) {
            for (int j = 0; j <= 30 ; j++) {
                dp[i][j]=-1;
            }
        }

        // 결과 출력
        System.out.println(run(N, K));
    }


    static long run(int n, int k) { // 재귀
        if(k == 1 || k == n || n==1) { // k[1, n-1] => 1
            return 1;
        }
        if(dp[n][k] !=-1){
            return dp[n][k];
        }

        return dp[n][k] = run(n - 1, k - 1) + run(n - 1, k); // prevN[k-1]+prevN[k]
    }
}
