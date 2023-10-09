package 김진아.week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1890_점프 {

    final static int DIRECTIONS = 2;
    final static int[] dx = { 1, 0 };
    final static int[] dy = { 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(input[j]);
        }

        long[][] route = new long[N][N];
        route[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || route[i][j] == 0) continue;

                for (int k = 0; k < DIRECTIONS; k++) {
                    int x = i + map[i][j] * dx[k];
                    int y = j + map[i][j] * dy[k];

                    if (x < 0 || x >= N || y < 0 || y >= N) continue;

                    route[x][y] += route[i][j];
                }
            }
        }

        writer.write(Long.toString(route[N - 1][N - 1]));

        writer.close();
        reader.close();
    }

}
