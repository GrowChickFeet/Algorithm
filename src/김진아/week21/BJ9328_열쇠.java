package 김진아.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ9328_열쇠 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int h;
    static int w;
    static int key;
    static int document;
    static int[][] visited;
    static int[][] building;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int testCase = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testCase; i++) {
            String[] input = reader.readLine().split(" ");
            h = Integer.parseInt(input[0]);
            w = Integer.parseInt(input[1]);

            building = new int[h][w];
            for (int j = 0; j < h; j++) {
                String line = reader.readLine();
                for (int k = 0; k < w; k++) {
                    char space = line.charAt(k);
                    if (space == '.') building[j][k] = 0;
                    else if (space == '*') building[j][k] = -100;
                    else if (space == '$') building[j][k] = 100;
                    else if (space >= 'a' && space <= 'z') building[j][k] = space - 'a' + 1;
                    else building[j][k] = -(space - 'A' + 1);
                }
            }

            String keys = reader.readLine();
            key = 1;
            if (!keys.equals("0")) {
                int count = keys.length();
                for (int j = 0; j < count; j++) key |= (1 << (keys.charAt(j) - 'a' + 1));
            }

            builder.append(steal()).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int steal() {
        document = 0;
        visited = new int[h][w];

        boolean explorable = true;
        while (explorable) {
            explorable = false;

            for (int i = 0; i < h; i++) {
                if (accessible(i, 0) && visited[i][0] != key) {
                    explorable = true;
                    steal(i, 0);
                }

                if (accessible(i, w - 1) && visited[i][w - 1] != key) {
                    explorable = true;
                    steal(i, w - 1);
                }
            }

            for (int i = 0; i < w; i++) {
                if (accessible(0, i) && visited[0][i] != key) {
                    explorable = true;
                    steal(0, i);
                }

                if (accessible(h - 1, i) && visited[h - 1][i] != key) {
                    explorable = true;
                    steal(h - 1, i);
                }
            }
        }

        return document;
    }

    static void steal(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        if (building[x][y] >= 1 && building[x][y] <= 26) {
            key |= (1 << building[x][y]);
            building[x][y] = 0;
        } else if (building[x][y] == 100) {
            document++;
            building[x][y] = 0;
        }

        visited[x][y] |= key;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w || !accessible(nx, ny) || visited[nx][ny] == key) continue;

                if (building[nx][ny] >= 1 && building[nx][ny] <= 26) {
                    key |= (1 << building[nx][ny]);
                    building[nx][ny] = 0;
                } else if (building[nx][ny] == 100) {
                    document++;
                    building[nx][ny] = 0;
                }

                visited[nx][ny] |= key;
                queue.offer(new int[] { nx, ny });
            }
        }
    }

    static boolean accessible(int x, int y) {
        if (building[x][y] == -100) return false;
        if (building[x][y] >= -26 && building[x][y] <= -1) return ((1 << -building[x][y]) & key) != 0;
        return true;
    }

}
