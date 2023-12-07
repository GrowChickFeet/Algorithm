package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ1926_그림 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int n;
    static int m;
    static boolean[][] picture;

    static int count;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        picture = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            input = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) picture[i][j] = input[j].equals("1");
        }

        count();

        writer.write(new StringBuilder().append(count).append("\n").append(max).toString());

        writer.close();
        reader.close();
    }

    static void count() {
        count = 0;
        max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (picture[i][j]) {
                    count++;
                    count(i, j);
                }
            }
        }
    }

    static void count(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        int size = 1;
        picture[x][y] = false;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || !picture[nx][ny]) continue;

                size++;
                picture[nx][ny] = false;
                queue.offer(new int[] { nx, ny });
            }
        }

        max = Math.max(size, max);
    }

}
