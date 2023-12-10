package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BJ1058_친구 {
    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                cost[i][j] = (line[j - 1] == 'Y' ? 1 : INF);
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }

        int result = 0;

        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (cost[i][j] <= 2) {
                    count++;
                }
            }
            result = Math.max(count, result);
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
