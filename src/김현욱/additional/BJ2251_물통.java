package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ2251_물통 {
    static Set<Integer> result = new HashSet<>();
    static boolean[][][] visited = new boolean[201][201][201];
    static int[] cap = new int[3];

    static void dfs(int[] arr) {
        if (arr[0] == 0) {
            result.add(arr[2]);
        }
        for (int i = 0; i < 3; i++) {//빠지는거
            for (int j = 0; j < 3; j++) {//채워지는거
                if (i == j) continue;

                int f = Math.min(arr[i], cap[j] - arr[j]);

                if (!visited[arr[0] - (i == 0 ? f : 0) + (j == 0 ? f : 0)]
                        [arr[1] - (i == 1 ? f : 0) + (j == 1 ? f : 0)]
                        [arr[2] - (i == 2 ? f : 0) + (j == 2 ? f : 0)]) {
                    visited[arr[0] - (i == 0 ? f : 0) + (j == 0 ? f : 0)]
                            [arr[1] - (i == 1 ? f : 0) + (j == 1 ? f : 0)]
                            [arr[2] - (i == 2 ? f : 0) + (j == 2 ? f : 0)] = true;
                    int[] copy = Arrays.copyOf(arr, arr.length);
                    copy[0] = copy[0] - (i == 0 ? f : 0) + (j == 0 ? f : 0);
                    copy[1] = copy[1] - (i == 1 ? f : 0) + (j == 1 ? f : 0);
                    copy[2] = copy[2] - (i == 2 ? f : 0) + (j == 2 ? f : 0);
                    dfs(copy);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        cap[0] = Integer.parseInt(st.nextToken());
        cap[1] = Integer.parseInt(st.nextToken());
        cap[2] = Integer.parseInt(st.nextToken());

        int[] arr = new int[]{0, 0, cap[2]};
        dfs(arr);

        StringBuilder sb = new StringBuilder();
        result.stream().sorted().forEach(v -> sb.append(v).append(' '));
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
