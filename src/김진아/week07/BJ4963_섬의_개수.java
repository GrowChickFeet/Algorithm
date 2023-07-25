import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    final static int DIRECTIONS = 8;
    final static int[] dx = { 1, 1, 1, 0, 0, -1, -1, -1 };
    final static int[] dy = { 1, 0, -1, 1, -1, 1, 0, -1 };

    static int w;
    static int h;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        w = Integer.parseInt(input[0]);
        h = Integer.parseInt(input[1]);

        while (w != 0 && h != 0) {
            map = new int[h][w];
            for (int i = 0; i < h; i++) {
                input = reader.readLine().split(" ");
                for (int j = 0; j < w; j++) map[i][j] = Integer.parseInt(input[j]);
            }

            builder.append(count()).append("\n");

            input = reader.readLine().split(" ");
            w = Integer.parseInt(input[0]);
            h = Integer.parseInt(input[1]);
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int count() {
        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 1) {
                    count(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    static void count(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        map[x][y] = 0;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == 0) continue;

                map[nx][ny] = 0;
                queue.offer(new int[] { nx, ny });
            }
        }
    }

}
