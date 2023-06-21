/*
https://www.acmicpc.net/problem/15989
 */

/*
- 합을 이루고 있는 수의 순서만 다른 것은 같은 것으로 친다
    => 오름차순으로만 갈 수 있도록 함
        - 1로 끝나는 것은 1로만 구성
        - 2로 끝나는 것은 1,2로만 구성
        - 3로 끝나는 것은 1,2,3로만 구성
- 'arr[목표합][마지막 숫자] = 경우의 수'로 나타낼 경우
    - 기본값
        - arr[1][1] = 1 // (1)
        - arr[2][1] = 1 // (1+1)
        - arr[2][2] = 1 // (2)
        - arr[3][1] = 1 // (1+1+1)
        - arr[3][2] = 1 // (1+2)
        - arr[3][3] = 1 // (3)
    - 4인 경우 예시 => arr[4][1] + arr[4][2] + arr[4][3]
        - arr[4][1] = arr[3][1] = 1 // 1을 더하기 전 1로 끝나는 경우의 수 (1+1+1+1)
        - arr[4][2] = arr[2][1] + arr[2][2] = 2 // 2을 더하기 전 1, 2로 끝나는 경우의 수 (1+1+2, 2+2)
        - arr[4][3] = arr[1][1] + arr[1][2] + arr[1][3] = 1 // 3을 더하기 전 1, 2, 3로 끝나는 경우의 수 (1+3)
    - [일반화] n일 때의 경우의 수 => arr[n][1] + arr[n][2] + arr[n][3] // 합이 n일 때 1,2,3으로 끝나는 경우
        - arr[n][1] = arr[n-1][1]
        - arr[n][2] = arr[n-2][1] + arr[n-2][2]
        - arr[n][3] = arr[n-3][1] + arr[n-3][2] + arr[n-3][3]

- 테스트케이스 반복할 때 반복 계산을 피하기위해 초기에 미리 10,000인 경우를 전부 구해놓고 답 도출하기!!

 */
package 남현실.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ15989_1_2_3더하기4 {
    static int[] numbers = {1, 2, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        int N;

        // 미리 경우의수 10000까지 전부 구해놓기
        int[][] arr = new int[10001][4];
        arr[1][1] = arr[2][1] = arr[2][2] = arr[3][1] = arr[3][2] = arr[3][3] = 1;

        for (int i = 4; i <= 10000; i++) {
            arr[i][1] = arr[i-1][1];
            arr[i][2] = arr[i-2][1] + arr[i-2][2];
            arr[i][3] = arr[i-3][1] + arr[i-3][2] + arr[i-3][3];
        }


        StringBuilder result = new StringBuilder();
        for (int tc = 0; tc < TC; tc++) {
            N = Integer.parseInt(br.readLine());
            result.append(arr[N][1]+arr[N][2]+arr[N][3]).append("\n");
        }
        System.out.println(result.toString());
        br.close();
    }
}
