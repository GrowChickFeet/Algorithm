package 김현욱.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2448_별찍기_11 {
    static char origin[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        origin = new char[n][n * 2 - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(origin[i], ' ');
        }

        draw(n, 0, n - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                sb.append(origin[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void draw(int n, int x, int y) {
        if (n == 3) {
            origin[x][y] = '*';
            origin[x + 1][y - 1] = origin[x + 1][y + 1] = '*';
            for (int i = 0; i < 5; i++) {
                origin[x + 2][y - 2 + i] = '*';
            }
            return;
        }

        draw(n / 2, x, y);
        draw(n / 2, x + n / 2, y - n / 2);
        draw(n / 2, x + n / 2, y + n / 2);
    }

}
