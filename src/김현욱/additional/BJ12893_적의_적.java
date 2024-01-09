package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ12893_적의_적 {
    public static boolean dfs(List<Integer>[] graph, boolean[] visited, boolean[] check, int node) {
        boolean result = true;

        for (int next : graph[node]) {
            if (!visited[next]) {
                visited[next] = true;
                check[next] = !check[node];
                result = result && dfs(graph, visited, check, next);
            } else if (check[next] == check[node]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean result = true;
        if (!(n == 1 || m == 0)) {
            boolean[] visited = new boolean[n + 1];
            boolean[] check = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    check[i] = true;
                    result = result && dfs(graph, visited, check, i);
                }
            }
        }
        bw.write(Integer.toString(result ? 1 : 0));
        br.close();
        bw.close();
    }
}
