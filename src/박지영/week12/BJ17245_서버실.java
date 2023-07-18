package 박지영.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17245_서버실 {
    static int N, mid, min, max = 10000000;
    static long totalCnt;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                totalCnt += map[i][j];
            }
        }

        System.out.print(binarySearch());
    }

    static int binarySearch() {
        while(max > min ) {
            mid = (max + min) / 2;
            long count = countComputer(mid);
            if (count >= totalCnt / 2.0) {
                max = mid;
            } else {
                min = mid+1;
            }
        }
        return max;
    }

    static long countComputer(int n) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += Math.min(n, map[i][j]);
            }
        }
        return sum;
    }

}