package 남현실.week5;

/*
https://www.acmicpc.net/problem/16493
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/*
[입력]
- N: 남은 기간 (1 <= N <= 200)
- M: 챕터 수 (1 <= M <= 20)
- 각 챕터 당
    - 소요 일 수 (1 <= x <= 20)
    - 페이지수 (1 <= y <= 300)


[출력]
- N일간 읽을 수 있는 최대 페이지 수
*/

/*
해당 일의 최대 페이지수를 구하고 업데이트해준다
Max(기존+현재(현재 포함O), 기존(현재 포함X))을 비교하면서 진행한다
- 앞에서부터 순차적으로 검사할 경우: 2차원 배열 (현재값 업데이트로 인해 전 값을 가지고 있어야하기 떄문)
- 뒤에서부터 순차적으로 검사할 경우: 1차원 배열 (업데이트 하는 값 전 값을 비교하는데 영향을 안 미치기 떄문)
 */
public class BJ16493_최대_페이지_수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]); // 일수
        int M = Integer.parseInt(temp[1]); // 챕터의수

        int[] dayInfo = new int[N + 1];

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int day = Integer.parseInt(temp[0]); // 걸리는 시간
            int page = Integer.parseInt(temp[1]); // 페이지 수
            for (int d = N; d >= day; d--) { // 걸리는 시간보다 적으면 업데이트 하지 않음 (못 읽음)
                dayInfo[d] = Math.max(dayInfo[d - day] + page, dayInfo[d]); // 현재 포함 vs 현재 포함 X
            }
        }
        System.out.println(dayInfo[N]);
    }
}
