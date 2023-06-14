package 박지영.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ6603_로또 {
    static int K;
    static int[] result, arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        while (K != 0) {
            arr = new int[K];
            result = new int[6];
            visited = new boolean[K];
            for (int i = 0; i < K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            combination(0, 0);
            sb.append("\n");
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb.toString());
    }

    public static void combination(int cnt, int start) {
        if (cnt == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < K; i++) {
            if (!visited[i]) {
                result[cnt] = arr[i];
                visited[i] = true;
                combination(cnt + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
