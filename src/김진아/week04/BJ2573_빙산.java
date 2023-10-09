package 김진아.week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Queue;
import java.util.LinkedList;

public class BJ2573_빙산 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int N;
    static int M;
    static int[][] ice;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        ice = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) ice[i][j] = Integer.parseInt(input[j]);
        }

        writer.write(Integer.toString(min()));

        writer.close();
        reader.close();
    }

    static int min() {
        int time = 0;

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ice[i][j] > 0) count++;
            }
        }

        while (count > 0) {
            if (seperated()) return time;

            int[][] melting = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (ice[i][j] == 0) continue;

                    melting[i][j] = ice[i][j];
                    for (int k = 0; k < DIRECTIONS; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && ice[nx][ny] == 0) melting[i][j]--;
                    }

                    if (melting[i][j] < 0) melting[i][j] = 0;
                    if (melting[i][j] == 0) count--;
                }
            }

            ice = melting;
            time++;
        }

        return 0;
    }

    static boolean seperated() {
        boolean[][] visited = new boolean[N][M];
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ice[i][j] == 0 || visited[i][j]) continue;

                Queue<int[]> queue = new LinkedList<>();

                visited[i][j] = true;
                queue.offer(new int[] { i, j });

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();

                    for (int k = 0; k < DIRECTIONS; k++) {
                        int nx = now[0] + dx[k];
                        int ny = now[1] + dy[k];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M || ice[nx][ny] == 0 || visited[nx][ny]) continue;

                        visited[nx][ny] = true;
                        queue.offer(new int[] { nx, ny });
                    }
                }

                count++;
            }
        }

        return count > 1;
    }

}
