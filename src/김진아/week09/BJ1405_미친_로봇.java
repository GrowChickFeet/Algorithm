package 김진아.week09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1405_미친_로봇 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 0, 0, 1, -1 };
    final static int[] dy = { 1, -1, 0, 0 };

    static int N;
    static double[] percentage;

    static double sum;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        percentage = new double[DIRECTIONS];
        for (int i = 0; i < DIRECTIONS; i++) percentage[i] = Integer.parseInt(input[i + 1]) / 100.0;

        sum = 0;
        boolean[][] visited = new boolean[N * 2 + 1][N * 2 + 1];
        visited[N][N] = true;
        find(0, N, N, 1, visited);

        writer.write(Double.toString(sum));

        writer.close();
        reader.close();
    }

    static void find(int count, int x, int y, double now, boolean[][] visited) {
        if (count == N) {
            sum += now;
            return;
        }

        for (int i = 0; i < DIRECTIONS; i++) {
            if (percentage[i] == 0) continue;

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            find(count + 1, nx, ny, now * percentage[i], visited);
            visited[nx][ny] = false;
        }
    }

}
