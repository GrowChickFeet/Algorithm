package 김현욱.week26;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1303_전쟁 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] mv = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static boolean isIn(int m, int n, int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] maze = new char[m][n];
        for (int i = 0; i < m; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        int[] result = new int[2];
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int size = 0;
                    Queue<Point> points = new ArrayDeque<>();
                    char team = maze[i][j];
                    visited[i][j] = true;
                    points.offer(new Point(i, j));

                    while (!points.isEmpty()) {
                        Point point = points.poll();
                        int x = point.x;
                        int y = point.y;
                        size++;

                        for (int d = 0; d < mv.length; d++) {
                            int nx = x + mv[d][0];
                            int ny = y + mv[d][1];

                            if (isIn(m, n, nx, ny) && !visited[nx][ny] && maze[nx][ny] == team) {
                                visited[nx][ny] = true;
                                points.offer(new Point(nx, ny));
                            }
                        }
                    }

                    result[team == 'W' ? 0 : 1] += size * size;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(result[0]).append(' ').append(result[1]);
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
