package 남현실.week7;

/*
https://www.acmicpc.net/problem/9934
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[입력]
- K (1<=K<=10)
- 방문한 빌딩 번호
    - 중복 X
    - 구간 [1,2^K]에 포함

[조건]
- 깊이가 K인 이진트리
- 도시에 있는 모든 빌딩에 들어감
- 들어간 순서대로 번호를 종이에 적어놓음
    순서:
    1. start: 루트
    2. 왼쪽 자식 검사
        - 방문한적 없다면: 왼쪽으로 이동
        - 있다면: 오른쪽 자식으로 이동
    3. 모든 자식 빌딩 방문했다면 부모 노드로 이동
    4. 끝까지 들어갔으면 들어간 건물에 대하여 메모
=> 트리 중위 순회


[출력]
K줄에 걸쳐서 정답 출력
- i번쨰줄: 레벨이 i인 빌딩 번호 출력 (왼->오)

1 / 2 3 / 4 5 6 7 / ...
예1) [2] 2 1 3 == 2^1+0 2^0+0 2^1+1
예2) [3] 4 2 5 1 6 3 7 == 2^2+0 2^1+0 2^2+1 2^0+0 2^2+2 2^1+1 2^2+3


 */
public class BJ9934_완전_이진_트리 {
    static int K;
    static String[] info, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        int size = (int) Math.pow(2, K) - 1; // 전체 크기 2^K -1
        tree = new String[size];

        info = br.readLine().split(" ");

        explore(0, 0, size / 2, size / 2);


        // depth별로 나눠서 프린트
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < K; i++) {
            for (int j = (int) Math.pow(2, i) - 1; j < (int) Math.pow(2, i + 1) - 1; j++) {
                result.append(tree[j]).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    /*
    부모노드들(중간값)을 순차적으로 탐색
    왼쪽 오른쪽 검사 (리프 노드까지 반복)
    ----
         mid
    left     right

     */
    static void explore(int depth, int offset, int midIdx, int halfsize) {
        int pivot = (int) Math.pow(2, depth) - 1 + offset; // 1차원 배열상의 인덱스 (2^depth-1) + offset
        tree[pivot] = info[midIdx]; // 현재 검사값을 넣음

        if (depth == K - 1) { // 리프 노드면 멈춤
            return;
        }
        halfsize /= 2;
        offset *= 2;
        explore(depth + 1, offset, midIdx - halfsize - 1, halfsize); // 왼쪽
        explore(depth + 1, offset + 1, midIdx + halfsize + 1, halfsize); // 오른쪽

    }
}
