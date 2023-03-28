package 남현실.week5;

/*
https://www.acmicpc.net/problem/2559
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[입력]
- N: 온도를 측정한 전체 날짜의 수 (2 <= N <= 100,000)
- K: 합을 구하기 위한 연속적인 날짜의 수 (1 <= K <= N)
- 매일 측정한 온도 t (-100 <= t <= 100)

[출력]
- 연속적인 며칠동안의 온도 합이 가장 큰 값

[풀이]
- 순차적으로 며칠동안의 온도 합을 구한다
  - 며칠동안의 온도 합을 구한 후 값을 저장한다
    - 이때 첫번째 값을 저장한다
  - 다음날에 전날의 합을 가져와 끝의 온도를 더한고 전에 저장한 첫번째 값을 빼서 합을 구한 후 저장한다
    - 이때 첫번째 값을 저장한다
  - 각 합의 최대값을 비교하며 이를 반복한다
 */
public class BJ2559_수열 {
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        int[] Ts = new int[N];

        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            Ts[i] = Integer.parseInt(temp[i]);
        }


        // 첫번째 온도 합 구하기
        long prevFirst, prevSum, curSum;
        long max;
        curSum = 0;
        for (int i = 0; i < K; i++) {
            curSum += Ts[i];
        }
        max = curSum;

        prevSum = curSum;
        prevFirst = Ts[0];


        // 온도 최대값 구하기
        for (int i = 1; i <= N-K; i++) {
            curSum = prevSum + Ts[i+K-1] - prevFirst;
            max = Math.max(curSum, max);

            prevSum = curSum;
            prevFirst = Ts[i];
        }

        // 최댓값 출력
        System.out.println(max);
    }
}
