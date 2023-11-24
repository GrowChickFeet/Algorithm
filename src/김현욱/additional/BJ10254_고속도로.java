package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ10254_고속도로 {
    static StringTokenizer st;
    static int BASE = 110;
    static int MAX_V = BASE * 2;
    static int[][] c = new int[MAX_V][MAX_V];
    static int[][] f = new int[MAX_V][MAX_V];
    static int[][] d = new int[MAX_V][MAX_V];
    static int[] in, out;
    static int N, T, F;
    static int source = MAX_V - 2;
    static int sink = MAX_V - 1;
    static List<Integer>[] graph;

    public static int MCMF() {
        int result = 0;
        int count = 0;
        while (true) {
            int[] dist, prev;
            boolean[] inQ;
            inQ = new boolean[MAX_V];
            dist = new int[MAX_V];
            prev = new int[MAX_V];
            Arrays.fill(prev, -1);
            Arrays.fill(dist, Integer.MAX_VALUE);

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(source);
            inQ[source] = true;
            dist[source] = 0;

            while (!q.isEmpty()) {
                int cur = q.poll();
                inQ[cur] = false;
                for (int next : graph[cur]) {
                    if (c[cur][next] - f[cur][next] > 0
                            && dist[next] > dist[cur] + d[cur][next]) {
                        dist[next] = dist[cur] + d[cur][next];
                        prev[next] = cur;

                        if (!inQ[next]) {
                            inQ[next] = true;
                            q.offer(next);
                        }
                    }
                }
            }
            if (prev[sink] == -1) break;
            int minFlow = Integer.MAX_VALUE;
            for (int i = sink; i != source; i = prev[i]) {
                minFlow = Math.min(minFlow, c[prev[i]][i] - f[prev[i]][i]);
            }

            for (int i = sink; i != source; i = prev[i]) {
                result += d[prev[i]][i];
                f[prev[i]][i] += minFlow;
                f[i][prev[i]] -= minFlow;
            }
            count++;
        }
        return count == N ? result : -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        in = new int[N];
        out = new int[N];
        graph = new List[MAX_V];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            in[i] = Integer.parseInt(st.nextToken());
            graph[source].add(i);
            graph[i].add(source);
            c[source][i] = 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            out[i] = Integer.parseInt(st.nextToken());
//            //out노드의 in->out 연결
//            graph[i + BASE].add(i + BASE * 2);
//            c[i + BASE][i + BASE * 2] = 1;

            graph[i + BASE].add(sink);
            graph[sink].add(i + BASE);
            c[i + BASE][sink] = 1;
        }

        T = Integer.parseInt(br.readLine());
        F = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (in[i] < out[j]) {
                    c[i][j + BASE] = 1;
                    graph[i].add(j + BASE);
                    graph[j + BASE].add(i);
                }

                if (out[j] - in[i] < T) {
                    d[i][j + BASE] = Math.min(F, (int) Math.pow(T - (out[j] - in[i]), 2));
                    d[j + BASE][i] = -d[i][j + BASE];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int minResult = MCMF();
        if (minResult == -1) {
            sb.append(-1);
        } else {
            for (int i = 0; i < MAX_V; i++) {
                for (int j = 0; j < MAX_V; j++) {
                    f[i][j] = 0;
                    d[i][j] = -d[i][j];
                }
            }
            int maxResult = MCMF();
            sb.append(minResult).append(' ').append(-maxResult);
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
