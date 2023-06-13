package 남현실.additional;

/*
[입력]

[조건]
- N: 총 반친구 수(2<=N<=20, 재홍이 제외)
- M: 관계도 수 M(0<=M<=(N^2-N)/2, 최대 50 제한)
- 친한 친구 관계는 출석번호 u, v
    - 양방향
    - u != v


[출력]
무대에 최대한 세울 수 있는 친구의 수

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ15270_친구_팰린드롬 {
    static int N, M;

    static boolean[] visited;
    static int[][] pairs;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        visited = new boolean[N];
        pairs = new int[M][2];

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            pairs[i][0] = Integer.parseInt(temp[0]) - 1;
            pairs[i][1] = Integer.parseInt(temp[1]) - 1;
        }

        comb(0);

        System.out.println(max);
    }

    static void comb(int cnt) {
        if (cnt == M) {
            int tempNum = 0;
            for (boolean temp : visited) {
                if (temp == true) {
                    tempNum += 1;
                }
            }
            if (N - tempNum >= 1) {
                tempNum += 1;
            }
            max = Math.max(tempNum, max);
            return;
        }

        int a, b;
        a = pairs[cnt][0];
        b = pairs[cnt][1];

        if (!visited[a] && !visited[b]) {
            visited[a] = true;
            visited[b] = true;
            comb(cnt + 1);
            visited[a] = false;
            visited[b] = false;
        }
        comb(cnt + 1);
    }
}
