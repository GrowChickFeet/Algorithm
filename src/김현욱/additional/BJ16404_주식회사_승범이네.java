package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16404_주식회사_승범이네 {
    static StringTokenizer st;
    static int n, m;
    static int[] in, out;
    static List<Integer>[] graph;
    static int count = 0;
    static int[] sum;

    static void dfs(int cur) {
        in[cur] = ++count;
        for (int next : graph[cur]) {
            dfs(next);
        }
        out[cur] = count;
    }

    static void add(int num, int value) {
        if (num == 0) return;
        while (num <= n) {
            sum[num] += value;
            num = num + (num & -num);
        }
    }

    static void addMoney(int from, int to, int diff) {
        add(from, diff);
        add(to + 1, -diff);
    }

    static int get(int num) {
        int s = 0;
        while (num > 0) {
            s += sum[num];
            num = num - (num & -num);
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        in = new int[n + 1];
        out = new int[n + 1];
        sum = new int[n + 1];
        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (i == 1) continue;
            graph[boss].add(i);
        }

        dfs(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int w = Integer.parseInt(st.nextToken());
                int from = in[num];//직원 i
                int to = out[num];//직원 i 의 부하직원 중 마지막 사람
                addMoney(from, to, w);
            } else {
                sb.append(get(in[num])).append('\n');
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
