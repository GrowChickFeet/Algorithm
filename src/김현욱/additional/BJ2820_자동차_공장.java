package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2820_자동차_공장 {
    static StringTokenizer st;
    static int n, m;
    static int[] in, out, order, salaries;
    static int count = 0;
    static List<Integer>[] graph;

    static class Node {
        int num, salary;

        public Node(int num, int salary) {
            this.num = num;
            this.salary = salary;
        }
    }

    static class SegTree {
        long[] tree;
        long[] lazy;
        int n;

        public SegTree(int n, int[] order) {
            this.n = n;
            int h = (int) Math.ceil(Math.log(n) / Math.log(2));
            int size = (1 << (h + 1));
            tree = new long[size];
            lazy = new long[size];
            init(order, 1, 1, n);
        }

        private long init(int[] order, int node, int left, int right) {
            if (left == right) {
                return tree[node] = order[left];
            }
            int middle = (left + right) >> 1;

            return tree[node] = init(order, node * 2, left, middle)
                    + init(order, node * 2 + 1, middle + 1, right);
        }

        private void lazyUpdate(int node, int left, int right) {
            if (lazy[node] != 0) {
                tree[node] += lazy[node] * (right - left + 1);

                if (left != right) {//단말노드가 아니라면
                    lazy[node * 2] += lazy[node];
                    lazy[node * 2 + 1] += lazy[node];
                }
                lazy[node] = 0;
            }
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

        public void update(int node, int left, int right, int start, int end, long value) {
            lazyUpdate(node, left, right);
            if (end < left || right < start) {
                return;
            }
            if (start <= left && right <= end) {
                tree[node] += value;
                if (left != right) {
                    lazy[node * 2] += value;
                    lazy[node * 2 + 1] += value;
                }
                return;
            }
            int middle = (left + right) >> 1;
            update(node * 2, left, middle, start, end, value);
            update(node * 2 + 1, middle + 1, right, start, end, value);
        }
    }

    static void dfs(int node) {
        in[node] = ++count;
        for (int next : graph[node]) {
            dfs(next);
        }
        out[node] = count;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        in = new int[n + 1];
        out = new int[n + 1];
        order = new int[n + 1];
        salaries = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int salary = Integer.parseInt(st.nextToken());
            int parent = (i == 1 ? 0 : Integer.parseInt(st.nextToken()));
            graph[parent].add(i);
            salaries[i] = salary;
        }

        dfs(1);
        for (int i = 1; i <= n; i++) {
            order[in[i]] = salaries[i];
        }
        SegTree seg = new SegTree(n, order);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            if (op.equals("p")) {
                int target = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                if (in[target] + 1 <= out[target]) {
                    seg.update(1, 1, n, in[target] + 1, out[target], value);
                }
            } else {
                int target = Integer.parseInt(st.nextToken());
                sb.append(seg.query(1, 1, n, in[target], in[target])).append('\n');
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
