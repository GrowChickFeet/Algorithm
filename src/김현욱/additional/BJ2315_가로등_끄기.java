package 김현욱.additional;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2315_가로등_끄기 {
    static final int MAX_N = 1000;
    static int n, m;
    static long[][][] dp = new long[MAX_N + 1][MAX_N + 1][2];
    static long[] wattageSum;
    static long[] positions;


    public static void resetDpTable() {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }

    public static long rec(int left, int right, int pos) {
        if (left == 1 && right == n) return 0;
        if (dp[left][right][pos] != -1) {
            return dp[left][right][pos];
        }

        long result = Long.MAX_VALUE;
        int nowIndex = (pos == 0 ? left : right);
        if (left > 1) {
            result = Math.min(result,
                    rec(left - 1, right, 0)
                            + (positions[nowIndex] - positions[left - 1])
                            * (wattageSum[n] - wattageSum[right] + wattageSum[left - 1]));
        }
        if (right < n) {
            result = Math.min(result,
                    rec(left, right + 1, 1)
                            + (positions[right + 1] - positions[nowIndex])
                            * (wattageSum[n] - wattageSum[right] + wattageSum[left - 1]));
        }
        return dp[left][right][pos] = result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        wattageSum = new long[n + 1];
        positions = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            positions[i] = Long.parseLong(st.nextToken());
            long wattage = Long.parseLong(st.nextToken());
            wattageSum[i] = wattageSum[i - 1] + wattage;
        }


        resetDpTable();
        long answer = rec(m, m, 0);
        bw.write(Long.toString(answer));
        br.close();
        bw.close();
    }
}
