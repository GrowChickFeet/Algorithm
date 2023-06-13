package 남현실.week6;

/*
https://www.acmicpc.net/problem/20040
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[입력]
- N: 점의 개수 (3 ≤ n ≤ 500,000)
- M: 진행된 차례 수 (3 ≤ m ≤ 1,000,000)
- i번째 차례에 해당 플레이어가 선택한 두 점의 번호 (1 ≤ i ≤ M)

[조건]
- n개의 점에는 0 부터 n − 1 까지 고유한 번호가 부여
- 이 중 어느 세 점도 일직선 위에 놓이지 않음
- 이전에 그린 선분을 다시 그을 수는 없음
- 이미 그린 다른 선분과 교차하는 것은 가능


[출력]
- m번내 사이클
    - O: 사이클이 처음으로 만들어진 차례의 번호
    - X: 0

[풀이]
- union 사용해서 M번 안에 union이
    - 불가능한 경우 (순환 X): 0 반환
    - 가능한 경우 (순환 O): 카운트값 반환

*/

public class BJ20040_사이클_게임 {
    static int N;
    static int[] parents;

    public static void make() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }
        parents[bRoot] = aRoot; // ???: 순서차이가 있던가..?
        return true;
    }

    public static void main(String[] args) throws IOException {
        // 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]); // 점의 개수
        int M = Integer.parseInt(temp[1]); // 차례 수

        // 부모 정보 배열 만들기
        make();

        // M번 내에 유니온 가능한지 체크
        int a, b, cnt = 0;
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            a = Integer.parseInt(temp[0]);
            b = Integer.parseInt(temp[1]);

            if (!union(a, b)) { // 유니온 가능 체크
                break;
            }

            cnt++; // 카운트 새어주기
        }

        // 결과 출력
        System.out.println((cnt < M) ? cnt+1 : 0);
    }
}
