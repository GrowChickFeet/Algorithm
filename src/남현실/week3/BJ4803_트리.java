package 남현실.week3;

/*
https://www.acmicpc.net/problem/4803
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ4803_트리 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int vCnt, eCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp;
        int N, M;


        int from, to;
        int tcCnt = 1, treeCnt;
        StringBuilder result = new StringBuilder();
        while (true) {
            // get N, M input
            temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);

            if (N == 0 && M == 0) { // end input
                break;
            }

            // get graph input
            graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                temp = br.readLine().split(" ");
                from = Integer.parseInt(temp[0]) - 1;
                to = Integer.parseInt(temp[1]) - 1;
                graph[from].add(to);
                graph[to].add(from);
            }
            treeCnt = 0;
            visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    continue;
                }
                if (checkTree(i)) {
                    treeCnt++;
                }
            }

            result.append("Case").append(" ").append(tcCnt).append(":").append(" ");
            if (treeCnt == 0) {
                result.append("No trees.");
            } else if (treeCnt == 1) {
                result.append("There is one tree.");
            } else {
                result.append("A forest of ").append(treeCnt).append(" trees.");
            }
            result.append("\n");

            tcCnt++;
        }

        System.out.println(result.toString());
        br.close();
    }

    static boolean checkTree(int cur) {
        vCnt = 0;
        eCnt = 0;
        explore(0, cur); // edge 수 세기
        if (eCnt != vCnt - 1) { // 트리라면 eCnt = vCnt-1 이어야한다
            return false;
        }
        return true;
    }

    static void explore(int prev, int cur) {
        vCnt++;

        visited[cur] = true;

        for (int v : graph[cur]) {
            if (prev == v) { // 처음이거나 바로 전 정점일 경우
                continue;
            }
            eCnt++;
            if (visited[v]) {
                continue;
            }
            explore(cur, v);
        }
    }
}
