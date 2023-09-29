import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int N;
    static int M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < M; j++) map[i][j] = line.charAt(j) - '0';
        }

        writer.write(Integer.toString(min()));

        writer.close();
        reader.close();
    }

    static int min() {
        boolean[][][] visited = new boolean[N][M][2];
        Queue<int[]> queue = new LinkedList<>();

        visited[0][0][0] = true;
        queue.offer(new int[] { 0, 0, 0, 1 });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == N - 1 && now[1] == M - 1) return now[3];

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == 1 && now[2] == 1) continue;

                int wall = now[2] + map[nx][ny];
                if (visited[nx][ny][wall]) continue;

                visited[nx][ny][wall] = true;
                queue.offer(new int[] { nx, ny, wall, now[3] + 1 });
            }
        }

        return -1;
    }

}
