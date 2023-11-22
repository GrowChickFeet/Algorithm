package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ14699_관악산_등산 {
    static StringTokenizer st;
    static int[] dp, height;
    static List<Integer>[] graph;
    static int n, m;

    static int getDp(int cur, int h) {
        if (graph[cur].size() == 0) {
            return dp[cur] = 1;
        }
        if (dp[cur] != -1) {
            return dp[cur];
        }
        int result = 0;
        for (int next : graph[cur]) {
            if (height[cur] < height[next]) {
                result = Math.max(result, getDp(next, height[next]));
            }
        }
        return dp[cur] = result + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        height = new int[n + 1];
        dp = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        Arrays.fill(dp, -1);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(getDp(i, height[i])).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
