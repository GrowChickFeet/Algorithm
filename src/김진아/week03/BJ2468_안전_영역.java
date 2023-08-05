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
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws  IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(input[j]);
        }

        writer.write(Integer.toString(count()));

        writer.close();
        reader.close();
    }

    static int count() {
        int count = 0;

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) max = Math.max(map[i][j], max);
        }

        for (int i = 0; i < max; i++) count = Math.max(count(i), count);

        return count;
    }

    static int count(int height) {
        int count = 0;

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] > height) {
                    setIsland(height, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    static void setIsland(int height, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] <= height) continue;

                visited[nx][ny] = true;
                queue.offer(new int[] { nx, ny });
            }
        }
    }

}
