package 김현욱.additional;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ11378_열혈강호_4 {
    static int N, M, K;
    static ArrayList<Integer>[] graph;
    static int[] match;
    static boolean[] visited;

    static boolean matching(int num) {
        for (int next : graph[num]) {
            if (visited[next]) continue;
            visited[next] = true;

            if (match[next] == 0 || matching(match[next])) {
                match[next] = num;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int result = 0;
        match = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            visited = new boolean[M + 1];
            if (matching(i)) {
                result++;
            }
        }

        while (true) {
            int change = 0;

            for (int i = 1; i <= N && K > 0 && result < M; i++) {
                visited = new boolean[M + 1];
                if (matching(i)) {
                    K--;
                    result++;
                    change++;
                }
            }

            if (change == 0 || K == 0) {
                break;
            }
        }

        bw.write(Integer.toString(result));

        br.close();
        bw.close();
    }
}