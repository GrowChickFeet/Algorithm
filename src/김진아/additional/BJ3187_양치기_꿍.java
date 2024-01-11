package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ3187_양치기_꿍 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int R;
    static int C;
    static int[][] map;

    static int totalSheep;
    static int totalWolf;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = reader.readLine();
            for (int j = 0; j < C; j++) {
                char status = line.charAt(j);
                if (status == 'k') map[i][j] = 1;
                else if (status == 'v') map[i][j] = 2;
                else if (status == '#') map[i][j] = -1;
            }
        }

        count();

        writer.write(new StringBuilder().append(totalSheep).append(" ").append(totalWolf).toString());

        writer.close();
        reader.close();
    }

    static void count() {
        totalSheep = 0;
        totalWolf = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) count(i, j);
            }
        }
    }

    static void count(int x, int y) {
        int sheep = 0;
        int wolf = 0;

        Queue<int[]> queue = new LinkedList<>();

        if (map[x][y] == 1) sheep++;
        else if (map[x][y] == 2) wolf++;
        map[x][y] = -1;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) continue;

                if (map[nx][ny] == 1) sheep++;
                else if (map[nx][ny] == 2) wolf++;
                map[nx][ny] = -1;
                queue.offer(new int[] { nx, ny });
            }
        }

        if (sheep > wolf) totalSheep += sheep;
        else totalWolf += wolf;
    }

}
