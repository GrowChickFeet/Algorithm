package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ18227_성대나라의_물탱크 {
    static StringTokenizer st;
    static int n, c;
    static int[] in, out;
    static int[] depth;
    static long[] tree;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int count = 0;

    static void dfs(int node, int dep) {
        visited[node] = true;
        in[node] = ++count;
        depth[node] = dep;
        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next, dep + 1);
            }
        }
        out[node] = count;
    }

    public static void update(int node, int start, int end, int idx) {
        tree[node]++;
        if (start < end) {
            int middle = (start + end) >> 1;

            if (idx <= middle) {
                update(node * 2, start, middle, idx);
            } else {
                update(node * 2 + 1, middle + 1, end, idx);
            }
        }
    }

    public static long query(int node, int start, int end, int left, int right) {
        if (left <= start && end <= right) {
            return tree[node];
        }
        if (end < left || right < start) {
            return 0;
        }

        int middle = (start + end) >> 1;

        return query(node * 2, start, middle, left, right)
                + query(node * 2 + 1, middle + 1, end, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        in = new int[n + 1];
        out = new int[n + 1];
        depth = new int[n + 1];
        tree = new long[4 * n];
        visited = new boolean[n + 1];
        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(c, 1);
        StringBuilder sb = new StringBuilder();
        int query = Integer.parseInt(br.readLine());
        for (int q = 0; q < query; q++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) {
                update(1, 1, n, in[b]);
            } else {
                sb.append(depth[b] * query(1, 1, n, in[b], out[b])).append('\n');
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
