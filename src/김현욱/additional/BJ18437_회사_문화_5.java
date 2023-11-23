package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ18437_회사_문화_5 {
    static StringTokenizer st;
    static int[] in, out;
    static int count = 0;
    static int n, m;
    static List<Integer>[] graph;

    static void dfs(int cur) {
        in[cur] = ++count;
        for (int next : graph[cur]) dfs(next);
        out[cur] = count;
    }

    static class Seg {
        long[] tree;
        long[] lazy;

        public Seg(int n) {
            int h = (int) Math.ceil(Math.log(n) / Math.log(2));
            int size = 1 << (h + 1);
            this.tree = new long[size];
            this.lazy = new long[size];

            init(1, 1, n);
        }

        private long init(int node, int left, int right) {
            if (left == right) {
                return tree[node] = 1;
            }
            int middle = (left + right) >> 1;
            return tree[node] = init(node * 2, left, middle)
                    + init(node * 2 + 1, middle + 1, right);
        }

        public long query(int node, int left, int right, int start, int end) {
            lazyUpdate(node, left, right);
            if (start <= left && right <= end) {
                return tree[node];
            }
            if (end < left || right < start) {
                return 0;
            }

            int middle = (left + right) >> 1;
            return query(node * 2, left, middle, start, end)
                    + query(node * 2 + 1, middle + 1, right, start, end);
        }

        public void update(int node, int left, int right, int start, int end, int value) {
            lazyUpdate(node, left, right);
            if (start <= left && right <= end) {//범위안에 있으면
                lazy[node] = value;
                lazyUpdate(node, left, right);
                return;
            }
            if (end < left || right < start) {
                return;
            }
            int middle = (left + right) >> 1;
            update(node * 2, left, middle, start, end, value);
            update(node * 2 + 1, middle + 1, right, start, end, value);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        private void lazyUpdate(int node, int left, int right) {
            if (lazy[node] != 0) {
                if (left != right) {
                    lazy[node * 2 + 1] = lazy[node * 2] = lazy[node];
                }
                tree[node] = (lazy[node] == 1 ? (right - left + 1) : 0);
                lazy[node] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        graph = new List[n + 1];
        in = new int[n + 1];
        out = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            graph[parent].add(i);
        }

        dfs(1);

        Seg seg = new Seg(n);

        StringBuilder sb = new StringBuilder();
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (op == 3) {
                sb.append(seg.query(1, 1, n, in[v] + 1, out[v])).append('\n');
            } else {
                seg.update(1, 1, n, in[v] + 1, out[v], op == 1 ? 1 : -1);
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
