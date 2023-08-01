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
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            for (int j = 0; j < N; j++) map[i][j] = input.charAt(j) == '1' ? true : false;
        }

        writer.write(Integer.toString(min()));

        writer.close();
        reader.close();
    }

    static int min() {
        int[][] count = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) count[i][j] = Integer.MAX_VALUE;
        }

        Queue<int[]> queue = new LinkedList<>();

        count[0][0] = 0;
        queue.offer(new int[] { 0, 0 });

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (count[nx][ny] <= count[point[0]][point[1]] + (map[nx][ny] ? 0 : 1)) continue;

                count[nx][ny] = Math.min(count[point[0]][point[1]] + (map[nx][ny] ? 0 : 1), count[nx][ny]);
                queue.offer(new int[] { nx, ny });
            }
        }

        return count[N - 1][N - 1];
    }

}
