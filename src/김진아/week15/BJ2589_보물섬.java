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

    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < M; j++) map[i][j] = line.charAt(j) == 'L' ? 1 : 0;
        }

        max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) find(i, j);
            }
        }

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

    static void find(int x, int y) {
        boolean[][] visited = new boolean[N][M];

        Queue<int[]> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.offer(new int[] { x, y, 0 });

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            max = Math.max(point[2], max);

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] != 1) continue;

                visited[nx][ny] = true;
                queue.offer(new int[] { nx, ny, point[2] + 1 });
            }
        }
    }

}
