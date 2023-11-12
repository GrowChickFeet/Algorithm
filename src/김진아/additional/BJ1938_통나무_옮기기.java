package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

class Log {

    int count;
    int direction;
    int[] center;

    public Log(int count, int direction, int x, int y) {
        this.count = count;
        this.direction = direction;
        center = new int[] { x, y };
    }

}

public class BJ1938_통나무_옮기기 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < N; j++) map[i][j] = line.charAt(j);
        }

        writer.write(Integer.toString(min()));

        writer.close();
        reader.close();
    }

    static int min() {
        boolean[][][] visited = new boolean[N][N][2];
        Queue<Log> queue = new LinkedList<>();

        for (int i = 0; i < N * N; i++) {
            int x = i / N;
            int y = i % N;

            if (map[x][y] != 'B') continue;

            int direction = 0;
            if (y + 1 >= N || map[x][y + 1] != 'B') direction = 1;

            if (direction == 0) y++;
            else x++;

            visited[x][y][direction] = true;
            queue.offer(new Log(0, direction, x, y));
            break;
        }

        while (!queue.isEmpty()) {
            Log now = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now.center[0] + dx[i];
                int ny = now.center[1] + dy[i];

                if (invalid(nx, ny, now.direction) || visited[nx][ny][now.direction]) continue;
                if (end(nx, ny, now.direction)) return now.count + 1;

                visited[nx][ny][now.direction] = true;
                queue.offer(new Log(now.count + 1, now.direction, nx, ny));
            }

            if (!rotatable(now.center[0] - 1, now.center[1] - 1)) continue;

            int nd = (now.direction + 1) % 2;

            if (invalid(now.center[0], now.center[1], nd) || visited[now.center[0]][now.center[1]][nd]) continue;
            if (end(now.center[0], now.center[1], nd)) return now.count + 1;

            visited[now.center[0]][now.center[1]][nd] = true;
            queue.offer(new Log(now.count + 1, nd, now.center[0], now.center[1]));
        }

        return 0;
    }

    static boolean invalid(int x, int y, int direction) {
        if (x < 0 || x >= N || y < 0 || y >= N) return true;
        if (direction == 0) return y <= 0 || y >= N - 1 || map[x][y] == '1' || map[x][y - 1] == '1' || map[x][y + 1] == '1';
        return x <= 0 || x >= N - 1 || map[x][y] == '1' || map[x - 1][y] == '1' || map[x + 1][y] == '1';
    }

    static boolean end(int x, int y, int direction) {
        if (direction == 0) return map[x][y] == 'E' && map[x][y - 1] == 'E' && map[x][y + 1] == 'E';
        return map[x][y] == 'E' && map[x - 1][y] == 'E' && map[x + 1][y] == 'E';
    }

    static boolean rotatable(int x, int y) {
        if (x < 0 || x >= N - 2 || y < 0 || y >= N - 2) return false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[x + i][y + j] == '1') return false;
            }
        }
        return true;
    }

}
