package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ14716_현수막 {

    final static int DIRECTIONS = 8;
    final static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    final static int[] dy = { -1, 0, 1, -1, 1, -1, 0 , 1 };

    static int M;
    static int N;
    static boolean[][] banner;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);

        banner = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) banner[i][j] = input[j].equals("1");
        }

        writer.write(Integer.toString(count()));

        writer.close();
        reader.close();
    }

    static int count() {
        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (banner[i][j]) {
                    count(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    static void count(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        banner[x][y] = false;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || !banner[nx][ny]) continue;;

                banner[nx][ny] = false;
                queue.offer(new int[] { nx, ny });
            }
        }
    }

}
