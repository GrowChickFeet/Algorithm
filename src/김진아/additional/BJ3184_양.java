package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ3184_양 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int R;
    static int C;
    static char[][] farm;

    static int sheep;
    static int wolf;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        farm = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = reader.readLine();
            for (int j = 0; j < C; j++) farm[i][j] = line.charAt(j);
        }

        survive();

        writer.write(new StringBuilder().append(sheep).append(" ").append(wolf).toString());

        writer.close();
        reader.close();
    }

    static void survive() {
        sheep = 0;
        wolf = 0;
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (farm[i][j] != '#' && !visited[i][j]) survive(i, j);
            }
        }
    }

    static void survive(int x, int y) {
        int sheepCount = 0;
        int wolfCount = 0;

        Queue<int[]> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (farm[now[0]][now[1]] == 'v') wolfCount++;
            else if (farm[now[0]][now[1]] == 'o') sheepCount++;

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C || farm[nx][ny] == '#' || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new int[] { nx, ny });
            }
        }

        if (sheepCount > wolfCount) sheep += sheepCount;
        else wolf += wolfCount;
    }

}
