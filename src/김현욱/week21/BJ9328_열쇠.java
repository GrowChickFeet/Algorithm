package 김현욱.week21;

import java.io.*;
import java.util.*;

public class BJ9328_열쇠 {
    public static int[][] mv = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static boolean isIn(int h, int w, int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static boolean isDoor(char c) {
        return 'A' <= c && c <= 'Z';
    }

    public static boolean isKey(char c) {
        return 'a' <= c && c <= 'z';
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_case = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < test_case; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int result = 0;

            char[][] maze = new char[h + 2][w + 2];
            for (int i = 0; i < maze.length; i++) {
                Arrays.fill(maze[i], '.');
            }

            for (int i = 1; i <= h; i++) {
                char[] line = br.readLine().toCharArray();
                for (int j = 1; j <= w; j++) {
                    maze[i][j] = line[j - 1];
                }
            }

            Queue<Point>[] locked = new ArrayDeque['z' - 'a' + 1];
            for (int i = 0; i < locked.length; i++) {
                locked[i] = new ArrayDeque<>();
            }

            boolean[][] visited = new boolean[h + 2][w + 2];
            boolean[] keys = new boolean['z' - 'a' + 1];

            char[] hKey = br.readLine().toCharArray();
            if (hKey[0] != '0') {
                for (char k : hKey) {
                    keys[k - 'a'] = true;
                }
            }

            Queue<Point> q = new ArrayDeque<>();
            q.offer(new Point(0, 0));

            while (!q.isEmpty()) {
                Point now = q.poll();

                if (maze[now.x][now.y] == '$') {
                    result++;
                }

                for (int d = 0; d < mv.length; d++) {
                    int nx = now.x + mv[d][0];
                    int ny = now.y + mv[d][1];

                    if (!isIn(h + 2, w + 2, nx, ny) || maze[nx][ny] == '*' || visited[nx][ny]) continue;

                    if (isKey(maze[nx][ny])) {
                        int kIdx = maze[nx][ny] - 'a';
                        q.offer(new Point(nx, ny));
                        keys[kIdx] = true;
                        visited[nx][ny] = true;

                        while(!locked[kIdx].isEmpty()){
                            Point point = locked[kIdx].poll();
                            int x = point.x;
                            int y = point.y;

                            if (!visited[x][y]) {
                                visited[x][y] = true;
                                q.offer(point);
                            }
                        }

                    } else if (isDoor(maze[nx][ny])) {
                        char door = maze[nx][ny];
                        if (keys[door - 'A']) {//키가 존재한다면 go
                            visited[nx][ny] = true;
                            q.offer(new Point(nx, ny));
                        } else {//키가 존재하지 않는다면 lock에 가둠
                            locked[door - 'A'].add(new Point(nx, ny));
                        }
                    } else {
                        visited[nx][ny] = true;
                        q.offer(new Point(nx, ny));
                    }
                }
            }

            sb.append(result).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
