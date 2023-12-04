package 김진아.week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ1303_전쟁_전투 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int N;
    static int M;
    static int[][] map;

    static int[] power;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            String line = reader.readLine();
            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == 'B') map[i][j] = 1;
            }
        }

        power = new int[2];
        count();

        writer.write(new StringBuilder().append(power[0]).append(" ").append(power[1]).toString());

        writer.close();
        reader.close();
    }

    static void count() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != -1) count(i, j);
            }
        }
    }

    static void count(int x, int y) {
        int team = map[x][y];
        int count = 0;

        Queue<int[]> queue = new LinkedList<>();

        count++;
        map[x][y] = -1;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || map[nx][ny] != team) continue;

                count++;
                map[nx][ny] = -1;
                queue.offer(new int[] { nx, ny });
            }
        }

        power[team] += count * count;
    }

}
