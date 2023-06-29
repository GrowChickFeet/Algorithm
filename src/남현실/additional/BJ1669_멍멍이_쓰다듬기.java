package 남현실.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://www.acmicpc.net/problem/1669
 */

/*
규칙: /\ (오르락내리락) 대칭

1 - 1           1 [홀] 2*(1) -1
-- (1)^2 --


2 - 1 1         2 [짝] 2*(1)
-- ((1)+1)*(1) --

3 - 1 1 1       3
4 - 1 2 1       3 [홀] 2*(2) - 1
-- (2)^2 --

5 - 1 2 1 1     4
6 - 1 2 2 1     4 [짝] 2*(2)
-- ((2)+1)*(2) --

7 - 1 2 2 1 1   5
8 - 1 2 2 2 1   5
9 - 1 2 3 2 1   5 [홀] 2*(3) -1
-- (3)^2 --

N: 현재 제곱근
  - 홀 기준: N^2
    - 최소 개수: 2*N-1
  - 짝 기준: (N+1)*N
    - 최소 개수: 2*N

 */
public class BJ1669_멍멍이_쓰다듬기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int X = Integer.parseInt(temp[0]);
        int Y = Integer.parseInt(temp[1]);

        int diff = Y - X;

        if(diff == 0) {
            System.out.println(0);
            return;
        }

        int N = (int) Math.floor(Math.sqrt(diff));

        long oddPivot = (long) Math.pow(N,2);
        int evenPivot = (N + 1) * N;
        long nextOddPivot = (long) Math.pow(N+1,2); // max

        if(oddPivot == diff) {
            System.out.println(2*N-1); // 홀
        }
        else if (diff <= evenPivot) {
            System.out.println(2*N); // 짝
        }
        else if (diff < nextOddPivot) {
            System.out.println(2*(N+1)-1); // 다음 홀
        }
    }
}
