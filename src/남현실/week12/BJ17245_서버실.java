package 남현실.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ17245_서버실 {
    static int N;
    static long[][] map;
    static long total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new long[N][N];
        String[] temp;

        total = 0;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
                total += map[i][j];
            }
        }

        if (total == 0) {
            System.out.println(0);
            return;
        }
        int min = 0;
        int max = 10_000_000; // 10,000,000
        while (min < max) {
            int mid = (max + min) / 2;
            int rate = rate(mid);

            if (rate >= 50) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min);
    }

    static int rate(int height) {
        long cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += Math.min(height, map[i][j]);
            }
        }

        if (total == 0) {
            return 0;
        }
        return (int) ((cnt * 100) / total);
    }
}
