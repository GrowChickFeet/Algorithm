package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ25512_트리를간단하게색칠하는최소비용 {
    static int[][] colors;
    static ArrayList<Integer>[] graph;

    static long dfs(int node, boolean white) {
        if (graph[node].isEmpty()) {
            return colors[node][white ? 0 : 1];
        }

        long result = colors[node][white ? 0 : 1];
        for (int next : graph[node]) {
            result += dfs(next, !white);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        colors = new int[n][2];

        for (int i = 0; i < n -1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            colors[i][0] = Integer.parseInt(st.nextToken());
            colors[i][1] = Integer.parseInt(st.nextToken());
        }

        long w = dfs(0, true);
        long b = dfs(0, false);
        bw.write(Long.toString(Math.min(w,b)));
        br.close();
        bw.close();
    }
}
