package 남현실.week4;

/*
https://www.acmicpc.net/problem/15903
 */

/*
[입력]
- n: 카드 개수 (2 ≤ n ≤ 1,000)
- m: 합체 개수 (0 ≤ m ≤ 15×n)
- ai: 카드상태(1 ≤ ai ≤ 1,000,000)

[정보]
- x번 카드와 y번 카드를 골라 두 장에 쓰여진 수를 더함 (x != y)
- 계산한 값을 x번 카드, y번 카드 두장 모두 덮어 씀
- 이를 m번 하고 전체 점수가 낮은 것 찾기

[출력]
- 만들 수 있는 가장 작은 점수

[풀이]
- 우선순위 큐를 이용해서 가장 순차적으로 가장 작은 숫자 카드 2개를 뽑는다
- 두 카드 숫자를 더한 후 결과값을 우선순위 큐에 다시 2번 넣는다 (카드 2개 덮어쓰기)
- 이를 m번 반복한다
- 우선순위에 있는 모든 카드 숫자를 더한다

O(mlogn)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ15903_카드_합체_놀이 {
    public static void main(String[] args) throws IOException {
        // 입력값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);

        // 우서순위큐에 카드 집어넣기
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (String card: br.readLine().split(" ")) {
            pq.offer(Long.parseLong(card));
        }

        // m번 반복해서 카드 합치기
        long card1, card2;
        long sum;
        for (int i = 0; i < m; i++) {
            // 카드 2개 뽑기
            card1 = pq.poll();
            card2 = pq.poll();

            // 카드 합치기
            sum = card1+card2;

            // 합한 값으로 우선순위 큐에 2번 넣기
            pq.offer(sum);
            pq.offer(sum);
        }

        // 우선순위큐에 있는 모든 카드 합 구하기
        long result = 0;
        while (!pq.isEmpty()) {
            result += pq.poll();
        }

        // 결과 출력
        System.out.println(result);
    }
}
