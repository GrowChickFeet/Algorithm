package 김진아.week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ11123_양_한마리_양_두마리 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int H;
    static int W;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            String[] input = reader.readLine().split(" ");
            H = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);

            map = new boolean[H][W];
            for (int j = 0; j < H; j++) {
                String line = reader.readLine();
                for (int k = 0; k < W; k++) map[j][k] = line.charAt(k) == '#';
            }

            builder.append(count()).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int count() {
        int count = 0;
        for (int j = 0; j < H; j++) {
            for (int k = 0; k < W; k++) {
                if (map[j][k]) {
                    sheep(j, k);
                    count++;
                }
            }
        }
        return count;
    }

    static void sheep(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        map[x][y] = false;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= H || ny < 0 || ny >= W || !map[nx][ny]) continue;

                map[nx][ny] = false;
                queue.offer(new int[] { nx, ny });
            }
        }
    }

}
