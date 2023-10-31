package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ11407_책_구매하기_3 {
    static int n, m;
    static final int BIAS = 110;
    static final int MAX = BIAS * 2;
    static final int INF = Integer.MAX_VALUE;
    static int source = MAX - 2;
    static int sink = MAX - 1;
    static int[][] c = new int[MAX][MAX];
    static int[][] f = new int[MAX][MAX];
    static int[][] d = new int[MAX][MAX];
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        graph = new ArrayList[MAX];
        for (int i = 0; i < MAX; i++) {
            graph[i] = new ArrayList<>();
        }

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] person = new int[n];
        int[] bookstore = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            person[i] = Integer.parseInt(st.nextToken());
            c[i + BIAS][sink] = person[i];
            graph[i + BIAS].add(sink);
            graph[sink].add(i + BIAS);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            bookstore[i] = Integer.parseInt(st.nextToken());
            c[source][i] = bookstore[i];
            graph[source].add(i);
            graph[i].add(source);
        }

        for (int bs = 0; bs < m; bs++) {
            st = new StringTokenizer(br.readLine());
            for (int p = BIAS; p < BIAS + n; p++) {
                c[bs][p] = Integer.parseInt(st.nextToken());
                if (c[bs][p] != 0) {
                    graph[bs].add(p);
                    graph[p].add(bs);
                }
            }
        }

        for (int bs = 0; bs < m; bs++) {
            st = new StringTokenizer(br.readLine());
            for (int p = BIAS; p < BIAS + n; p++) {
                d[bs][p] = Integer.parseInt(st.nextToken());
                d[p][bs] = -d[bs][p];
            }
        }

        long result = 0;
        int count = 0;
        while (true) {
            int[] dist = new int[MAX];
            int[] prev = new int[MAX];
            boolean[] inQ = new boolean[MAX];
            Arrays.fill(dist, INF);
            Arrays.fill(prev, -1);

            Queue<Integer> q = new ArrayDeque<>();
            dist[source] = 0;
            inQ[source] = true;
            q.offer(source);

            while (!q.isEmpty()) {
                int cur = q.poll();

                inQ[cur] = false;

                for (int next : graph[cur]) {
                    if (c[cur][next] - f[cur][next] > 0
                            && dist[next] > dist[cur] + d[cur][next]) {
                        dist[next] = dist[cur] + d[cur][next];
                        prev[next] = cur;

                        if(!inQ[next]){
                            inQ[next] = true;
                            q.offer(next);
                        }
                    }
                }
            }

            if (prev[sink] == -1) break;

            int flow = INF;
            for (int i = sink; i != source; i = prev[i]) {
                flow = Math.min(flow, c[prev[i]][i] - f[prev[i]][i]);
            }

            for (int i = sink; i != source; i = prev[i]) {
                result += d[prev[i]][i] * flow;
                f[prev[i]][i] += flow;
                f[i][prev[i]] -= flow;
            }
        }

        for(int p=BIAS;p<BIAS+n;p++){
            count+=f[p][sink];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count).append('\n').append(result);
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
