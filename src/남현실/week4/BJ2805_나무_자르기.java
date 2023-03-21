package 남현실.week4;

/*
https://www.acmicpc.net/problem/2805
 */

/*
[입력]
- N: 나무의 수 (1 ≤ N ≤ 1,000,000)
- M: 나무의 길이 (1 ≤ M ≤ 2,000,000,000)
- 나무의 높이 정보 (1,000,000,000보다 작거나 같은 양의 정수 or 0)

[정보]
- 나무 M미터 필요
- 절단기 동작
  1. 절단기에 높이 H 지정
  2. 톱날이 땅으로부터 H미터 위로 올라감
  3. 한 줄에 연속해있는 나무 모두 절단

[출력]
적어도 M미터 나무를 가져가기위한 절단기 설정 높이 최댓값

[풀이]
이분탐색
- 0에서 max높이까지 이분탐색을 진행한다 (가능/불가능 좁혀가기)
- 해당 높이에서 M개 이상의 나무를 놓을 수 있는지 체크한다
- start, end차이가 0이거나 1일때 start(가능한 높이)를 출력한다
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2805_나무_자르기 {
    static int N, M;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        // 입력값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        int maxH = 0;
        trees = new int[N];
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(temp[i]);
            maxH = Math.max(maxH, trees[i]);
        }

        // 높이 탐색 시작
        int start = 0, end = maxH, mid;
        while (end - start > 1) { // start, end 1차이 났을 때 break (1차이 났을 때 동일한 값이 start쪽으로 가기 때문에 무한루프 발생)
            mid = (start + end) / 2;

            // 해당 높이 가능 여부 체크
            if (check(mid)) { // 가능
                start = mid;
            } else { // 불가능
                end = mid;
            }
        }

        // 결과 출력
        System.out.println(start);
    }

    // 전체 나무를 탐색 및 계산하여 현재 절단기 높이에서 M미터의 나무를 가져갈 수 있는지 확인
    static boolean check(int pivotH) { //pivotH: 현재 절단기 높이
        long sum = 0;
        for (int h : trees) {
            if (pivotH > h) { // 현재 절단기 높이보다 작으면 넘어감
                continue;
            }
            sum += h - pivotH; // 잘라갈 수 있는 길이 (현재 절단기 높이와 나무 높이의 차이) 더하기
        }
        return (sum >= M); // 전체
    }
}