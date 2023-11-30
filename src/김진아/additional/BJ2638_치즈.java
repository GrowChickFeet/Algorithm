package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ2638_치즈 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int N;
    static int M;
    static int[][] cheese;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        cheese = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) cheese[i][j] = Integer.parseInt(input[j]);
        }

        writer.write(Integer.toString(melting()));

        writer.close();
        reader.close();
    }

    static int melting() {
        int time = 0;

        while (remaining()) {
            int[][] status = copy();

            Queue<int[]> queue = new LinkedList<>();

            status[0][0] = -1;
            queue.offer(new int[] { 0, 0 });

            while (!queue.isEmpty()) {
                int[] now = queue.poll();

                for (int i = 0; i < DIRECTIONS; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || status[nx][ny] != 0) continue;

                    status[nx][ny] = -1;
                    queue.offer(new int[] { nx, ny });
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (cheese[i][j] != 1) continue;

                    int count = 0;
                    for (int k = 0; k < DIRECTIONS; k++) {
                        if (status[i + dx[k]][j + dy[k]] == -1) count++;
                    }

                    if (count >= 2) cheese[i][j] = 0;
                }
            }

            time++;
        }

        return time;
    }

    static boolean remaining() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 1) return true;
            }
        }
        return false;
    }

    static int[][] copy() {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) copy[i][j] = cheese[i][j];
        }
        return copy;
    }

}
