package 김현욱.week23;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11909_배열_탈출 {
    static int[][] mv = new int[][]{{0, 1}, {1, 0}};

    static boolean isIn(int n, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        Point point;
        int cost;

        public Node(Point point, int cost) {
            this.point = point;
            this.cost = cost;
        }

        public Node(int x, int y, int cost) {
            this.point = new Point(x, y);
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] maze = new int[n][n];
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Node> q = new ArrayDeque<>();
        dist[0][0] = 0;
        q.offer(new Node(0, 0, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();
            Point point = now.point;
            int x = point.x;
            int y = point.y;
            int cost = now.cost;

            if (cost != dist[x][y]) continue;

            for (int d = 0; d < mv.length; d++) {
                int nx = x + mv[d][0];
                int ny = y + mv[d][1];

                if (!isIn(n, nx, ny)) continue;
                int nextCost = Math.max(0, maze[nx][ny] - maze[x][y] + 1);
                if (dist[nx][ny] > dist[x][y] + nextCost) {
                    dist[nx][ny] = dist[x][y] + nextCost;
                    q.offer(new Node(nx, ny, dist[nx][ny]));
                }
            }
        }

        bw.write(Integer.toString(dist[n - 1][n - 1]));
        br.close();
        bw.close();
    }
}
