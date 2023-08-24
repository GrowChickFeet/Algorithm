import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Queue;
import java.util.LinkedList;

public class Main {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int N;
    static int M;
    static char[][] map;
    static int[] start;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'I') start = new int[] { i, j };
            }
        }

        int friend = meetPeople();
        writer.write(friend == 0 ? "TT" : Integer.toString(friend));

        writer.close();
        reader.close();
    }

    static int meetPeople() {
        int friend = 0;

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        visited[start[0]][start[1]] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 'X') continue;

                if (map[nx][ny] == 'P') friend++;
                visited[nx][ny] = true;
                queue.offer(new int[] { nx, ny });
            }
        }

        return friend;
    }

}
