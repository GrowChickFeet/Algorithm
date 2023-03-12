package 박지영.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ4803_트리 {
    static int N, M;
    static boolean possible;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int test_case = 0;
        while (N!=0 && M!=0) {
            test_case++;
            adjList = new ArrayList[N+1];
            visited = new boolean[N+1];
            Arrays.fill(visited, false);
            for (int i = 1; i < N+1; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
//                if (start == end) continue;
                adjList[start].add(end);
                adjList[end].add(start);
            }

            int result = 0;
            for (int i = 1; i < N+1; i++) {
                if (!visited[i]) {
                    possible = true;
                    dfs(i, 0);
                    if (possible) result++;
                }

            };

            // ----- 출력 -----
            sb.append("Case ").append(test_case).append(": ");
            switch (result) {
                case 1:
                    sb.append("There is one tree.");
                    break;
                case 0:
                    sb.append("No trees.");
                    break;
                default:
                    sb.append("A forest of ").append(result).append(" trees.");
            }
            sb.append("\n");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb);
    }

    static void dfs(int node, int parent) {
        if (visited[node]) {
            possible = false;
            return;
        }
        visited[node] = true;
        for(int n: adjList[node]) {
            if (n!= parent) {
                dfs(n, node);
            }
        }
    }
}
