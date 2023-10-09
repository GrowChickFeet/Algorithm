package 김진아.week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ19235_청소년_상어 {

    final static int SIZE = 4;
    final static int SHARK = 100;
    final static int BLANK = -10;
    final static int DIRECTIONS = 8;
    final static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    final static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] map = new int[SIZE][SIZE];
        int[][] fish = new int[SIZE * SIZE][3];

        for (int i = 0; i < SIZE; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(input[j * 2]) - 1;
                fish[map[i][j]][0] = i;
                fish[map[i][j]][1] = j;
                fish[map[i][j]][2] = Integer.parseInt(input[j * 2 + 1]) - 1;
            }
        }

        max = 0;
        eat(0, 0, 0, map, fish);

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

    static void eat(int sum, int x, int y, int[][] map, int[][] fish) {
        sum += map[x][y] + 1;
        max = Math.max(sum, max);

        int direction = fish[map[x][y]][2];
        fish[map[x][y]][0] = -1;

        map[x][y] = SHARK;
        move(map, fish);
        map[x][y] = BLANK;

        for (int i = 1; i < SIZE; i++) {
            int nx = x + dx[direction] * i;
            int ny = y + dy[direction] * i;

            if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) break;
            if (map[nx][ny] == BLANK) continue;

            eat(sum, nx, ny, copy(map), copy(fish));
        }
    }

    static void move(int[][] map, int[][] fish) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            int x = fish[i][0];
            int y = fish[i][1];

            if (x == -1) continue;

            for (int j = 0; j < DIRECTIONS; j++) {
                int direction = (fish[i][2] + j) % DIRECTIONS;
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE || map[nx][ny] == SHARK) continue;

                map[x][y] = map[nx][ny];
                map[nx][ny] = i;

                fish[i][0] = nx;
                fish[i][1] = ny;
                fish[i][2] = direction;

                if (map[x][y] != BLANK) {
                    fish[map[x][y]][0] = x;
                    fish[map[x][y]][1] = y;
                }

                break;
            }
        }
    }

    static int[][] copy(int[][] origin) {
        int n = origin.length;
        int m = origin[0].length;

        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) copy[i][j] = origin[i][j];
        }
        return copy;
    }

}
