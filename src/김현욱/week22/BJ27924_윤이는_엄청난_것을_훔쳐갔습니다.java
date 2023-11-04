package 김현욱.week22;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ27924_윤이는_엄청난_것을_훔쳐갔습니다 {
    static int[][] dist;
    static ArrayList<Integer>[] graph;

    static void bfs(int node, int idx) {
        boolean[] visited = new boolean[graph.length];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{node, 0});
        visited[node] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int num = cur[0];
            int dst = cur[1];
            dist[num][idx] = dst;

            for (int next : graph[num]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, dst + 1});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        dist = new int[n + 1][3];
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        bfs(y, 0);
        bfs(d, 1);
        bfs(p, 2);

        boolean flag = false;
        for (int i = 0; i <= n; i++) {
            if (graph[i].size() == 1 && dist[i][0] < dist[i][1] && dist[i][0] < dist[i][2]) {
                flag = true;
                break;
            }
        }

        bw.write(flag ? "YES" : "NO");
        br.close();
        bw.close();
    }
}
