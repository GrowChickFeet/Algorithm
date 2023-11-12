package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ2311_왕복_여행 {
    static int MAX_V = 2020;
    static int OUT_BIAS = 1010;
    static int[][] d = new int[MAX_V][MAX_V];
    static int[][] f = new int[MAX_V][MAX_V];
    static int[][] c = new int[MAX_V][MAX_V];
    static ArrayList<Integer>[] graph = new ArrayList[MAX_V];

    public static void addLine(int from, int to, int cap, int cost) {
        graph[from].add(to);
        graph[to].add(from);
        c[from][to] = cap;
        d[from][to] = cost;
        d[to][from] = -cost;
    }

    public static long mcmf(int source, int sink) {
        int[] dist = new int[MAX_V];
        int[] prev = new int[MAX_V];
        boolean[] inQ = new boolean[MAX_V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(source);
        inQ[source] = true;
        dist[source] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            inQ[cur] = false;

            for (int next : graph[cur]) {
                if (dist[next] > dist[cur] + d[cur][next]
                        && c[cur][next] - f[cur][next] > 0) {
                    dist[next] = dist[cur] + d[cur][next];
                    prev[next] = cur;
                    if (!inQ[next]) {
                        q.offer(next);
                        inQ[next] = true;
                    }
                }
            }
        }
        long sum = 0;
        for (int i = sink; i != source; i = prev[i]) {
            sum += d[prev[i]][i];
            f[prev[i]][i]++;
            f[i][prev[i]]--;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long result = 0;
        int sink = n;
        int source = 1 + OUT_BIAS;

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            graph[i].add(i + OUT_BIAS);
            graph[i + OUT_BIAS].add(i);
            c[i][i + OUT_BIAS] = c[i + OUT_BIAS][i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            addLine(u + OUT_BIAS, v, 1, cost);
            addLine(v + OUT_BIAS, u, 1, cost);
        }

        result = mcmf(source,sink) + mcmf(source,sink);
        bw.write(Long.toString(result));
        br.close();
        bw.close();
    }
}
