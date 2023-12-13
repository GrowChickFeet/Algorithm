package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ10976_피난 {
    static int n, m;
    static int INF = Integer.MAX_VALUE;

    static int flow(List<Integer>[] graph, int source, int sink, int n, int[][] c, int[][] f) {
        int result = 0;
        while (true) {
            int[] prev = new int[n];
            Arrays.fill(prev, -1);

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(source);

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int next : graph[cur]) {
                    if (c[cur][next] - f[cur][next] > 0 && prev[next] == -1) {
                        prev[next] = cur;
                        q.offer(next);
                    }
                }
            }

            if (prev[sink] == -1) break;

            int minFlow = INF;
            for (int i = sink; i != source; i = prev[i]) {
                minFlow = Math.min(minFlow, c[prev[i]][i] - f[prev[i]][i]);
            }

            for (int i = sink; i != source; i = prev[i]) {
                f[prev[i]][i] += minFlow;
                f[i][prev[i]] -= minFlow;
            }

            result += minFlow;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            int[][] f = new int[n][n];
            int[][] c = new int[n][n];
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            int source = 0;
            int sink = n - 1;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;

                c[u][v] = (u == source || v == sink ? 1 : INF);
                graph[u].add(v);
                graph[v].add(u);
            }

            sb.append(flow(graph, source, sink, n, c, f)).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
