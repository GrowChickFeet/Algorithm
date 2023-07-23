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
    static boolean[][] food;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        food = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            input = reader.readLine().split(" ");
            food[Integer.parseInt(input[0]) - 1][Integer.parseInt(input[1]) - 1] = true;
        }

        writer.write(Integer.toString(max()));

        writer.close();
        reader.close();
    }

    static int max() {
        int max = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (food[x][y]) max = Math.max(size(x, y), max);
            }
        }
        return max;
    }

    static int size(int x, int y) {
        int size = 0;

        Queue<int[]> queue = new LinkedList<>();

        size++;
        food[x][y] = false;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || !food[nx][ny]) continue;

                size++;
                food[nx][ny] = false;
                queue.offer(new int[] { nx, ny });
            }
        }

        return size;
    }

}
