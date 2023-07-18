package 박지영.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ15989_123더하기4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int[][] arr = new int[10001][4];
        arr[1][1] = 1;
        arr[2][1] = 1;
        arr[2][2] = 1;
        arr[3][1] = 1;
        arr[3][2] = 1;
        arr[3][3] = 1;

        for (int i = 4; i < 10001; i++) {
            arr[i][1] = arr[i-1][1];
            arr[i][2] = arr[i-2][1] + arr[i-2][2];
            arr[i][3] = arr[i-3][1] + arr[i-3][2] + arr[i-3][3];
        }

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(arr[n][1] + arr[n][2] + arr[n][3]).append("\n");
        }

        System.out.print(sb);
    }
}
