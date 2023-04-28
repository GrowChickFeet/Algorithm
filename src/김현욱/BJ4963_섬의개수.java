package 김현욱.week7;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4963_섬의개수 {
    public static int h, w;
    public static int[][] mv = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    public static boolean isIn(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            int[][] maze = new int[h][w];
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    maze[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int result = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (maze[i][j] == 1 && !visited[i][j]) {
                        Queue<Integer> q = new LinkedList<>();
                        q.offer(i * w + j);
                        visited[i][j] = true;

                        while (!q.isEmpty()) {
                            int idx = q.poll();
                            int x = idx / w;
                            int y = idx % w;

                            for (int d = 0; d < mv.length; d++) {
                                int nx = x + mv[d][0];
                                int ny = y + mv[d][1];

                                if (isIn(nx, ny) && maze[nx][ny] == 1 && !visited[nx][ny]) {
                                    visited[nx][ny] = true;
                                    q.offer(nx * w + ny);
                                }
                            }
                        }
                        result++;
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
