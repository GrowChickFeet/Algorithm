package 김현욱.week25;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11123_양_한마리_양_두마리 {
    static int[][] mv = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isIn(int n, int m, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            char[][] maze = new char[n][m];
            for (int i = 0; i < n; i++) {
                maze[i] = br.readLine().toCharArray();
            }

            int result = 0;
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (maze[i][j] == '#' && !visited[i][j]) {
                        result++;
                        Queue<Point> q = new ArrayDeque<>();
                        visited[i][j] = true;
                        q.offer(new Point(i, j));

                        while (!q.isEmpty()) {
                            Point point = q.poll();
                            int x = point.x;
                            int y = point.y;

                            for (int d = 0; d < mv.length; d++) {
                                int nx = x + mv[d][0];
                                int ny = y + mv[d][1];

                                if (isIn(n, m, nx, ny) && !visited[nx][ny] && maze[nx][ny] == '#') {
                                    visited[nx][ny] = true;
                                    q.offer(new Point(nx, ny));
                                }
                            }
                        }
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
