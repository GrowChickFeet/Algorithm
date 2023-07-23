package 박지영.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 펠린드롬 : 좌우가 대칭인 문자
 *
 * 다이나믹프로그래밍으로 풀었다.
 *
 * 초기
 * - 1개일 경우에는 당연히 펠린드롬 -> 1로 초기화
 * - 2개일 경우에는 옆의 수와 같으면 펠린드롬 -> 1로 초기화
 *
 * DP 수행
 * 1. 양 끝의 수가 같다.
 * 2. 가운데가 펠린드롬.
 * ==> 펠린드롬 dp 이차원 배열을 1로 업데이트
 */
public class BJ10942_팰린드롬 {
    static int N, M;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+1][N+1];

        for (int i = 1; i < N+1; i++) {
            dp[i][i] =  1;      // 길이가 1개일 때: 자기 자신은 무조건 팰린드롬

            if (arr[i] == arr[i-1]) dp[i-1][i] = 1;     // 길이가 2일떄 : 바로 옆의 수와 똑같으면 펠린드롬
        }

        for (int i = 2; i < N; i++) {     // 길이
            for (int j = 1; j < N-i+1; j++) {       // 시작 위치
                if (arr[j] == arr[i+j] && dp[j+1][j+i-1] == 1) dp[j][j+i] = 1;      // 양끝이 같고 가운데가 팰린드롬일 때
            }
        }

        // 출력
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(dp[s][e]).append("\n");
        }

        System.out.print(sb.toString());
    }

}