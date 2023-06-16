package 남현실.week10;

/*
https://www.acmicpc.net/problem/11501
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
행동 종류
1. 주식 하나를 산다.
2. 원하는 만큼 가지고 있는 주식을 판다.
3. 아무것도 안한다.

2 <= N <= 1,000,000

최대이익?

 */
public class BJ11501_주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        int N;
        int[] stocks;
        String[] temp;
        long result;

        for (int tc = 0; tc < TC; tc++) {
            // init
            N = Integer.parseInt(br.readLine());
            stocks = new int[N];
            result = 0;

            temp = br.readLine().split(" ");
            for (int i = 0; i < N; i++) { // reverse inputs
                stocks[i] = Integer.parseInt(temp[N - i - 1]);
            }

            long cur, max = 0;
            for (int i = 0; i < N; i++) {
                cur = stocks[i];
                if (max < cur) {
                    max = cur;
                    continue;
                }

                result += (max - cur);
            }
            System.out.println(result);
        }

    }
}
