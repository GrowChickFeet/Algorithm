package 김현욱.week23;

import java.io.*;
import java.util.StringTokenizer;

public class BJ25587_배수로 {
    static StringTokenizer st;
    static int[] parent;
    static int[] sum;
    static int[] count;
    static int[] cap;
    static int result = 0;
    static int[] ov;

    static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    static boolean over(int num) {
        return cap[num] < sum[num];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;

        if (over(a)) {
            result -= count[a];
        }
        if (over(b)) {
            result -= count[b];
        }

        if (a < b) {
            parent[b] = a;
            sum[a] += sum[b];
            count[a] += count[b];
            cap[a] += cap[b];

            if (over(a)) {
                result += count[a];
            }
        } else if (a > b) {
            parent[a] = b;
            sum[b] += sum[a];
            count[b] += count[a];
            cap[b] += cap[a];
            if (over(b)) {
                result += count[b];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        cap = new int[n];//용량
        parent = new int[n];//부모
        sum = new int[n];//현재 비의 양
        count = new int[n];//묶인 도시 개수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            cap[i] = Integer.parseInt(st.nextToken());
            count[i] = 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sum[i] = Integer.parseInt(st.nextToken());
            if (over(i)) result++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                union(u, v);
            } else {
                sb.append(result).append('\n');
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
