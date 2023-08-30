import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    final static int DIRECTIONS = 6;
    final static int[] dx = { 1, -1, 0, 0, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1, 0, 0 };
    final static int[] dz = { 0, 0, 0, 0, 1, -1 };

    static int M;
    static int N;
    static int H;
    static int[][][] box;

    static int ripe;
    static int empty;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);

        box = new int[H][N][M];
        ripe = 0;
        empty = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                input = reader.readLine().split(" ");
                for (int k = 0; k < M; k++) {
                    box[i][j][k] = Integer.parseInt(input[k]);
                    if (box[i][j][k] == 1) ripe++;
                    else if (box[i][j][k] == -1) empty++;
                }
            }
        }

        if (ripe + empty == H * N * M) writer.write(Integer.toString(0));
        else writer.write(Integer.toString(day()));

        writer.close();
        reader.close();
    }

    static int day() {
        int day = 0;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[i][j][k] == 1) queue.offer(new int[] { i, j, k, 0 });
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] tomato = queue.poll();
            day = Math.max(tomato[3], day);

            for (int i = 0; i < DIRECTIONS; i++) {
                int nz = tomato[0] + dz[i];
                int nx = tomato[1] + dx[i];
                int ny = tomato[2] + dy[i];

                if (nz < 0 || nz >= H || nx < 0 || nx >= N || ny < 0 || ny >= M || box[nz][nx][ny] != 0) continue;

                box[nz][nx][ny] = 1;
                ripe++;

                queue.offer(new int[] { nz, nx, ny, tomato[3] + 1 });
            }
        }

        return ripe + empty == H * N * M ? day : -1;
    }

}
