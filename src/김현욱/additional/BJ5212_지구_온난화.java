package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ5212_지구_온난화 {
    public static int[][] mv = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static boolean isIn(int n, int m, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] maze = new char[n][m];

        for (int i = 0; i < n; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        List<int[]> removed = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int count = 0;
                for (int d = 0; d < mv.length; d++) {
                    int nx = i + mv[d][0];
                    int ny = j + mv[d][1];

                    if (!isIn(n, m, nx, ny) || maze[nx][ny] == '.') {
                        count++;
                    }
                }
                if (count >= 3) {
                    removed.add(new int[]{i, j});
                }
            }
        }

        for (int[] point : removed) {
            int x = point[0];
            int y = point[1];

            maze[x][y] = '.';
        }

        int minX = n - 1;
        int maxX = 0;
        int minY = m - 1;
        int maxY = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 'X') {
                    minX = Math.min(minX, i);
                    maxX = Math.max(maxX, i);
                    minY = Math.min(minY, j);
                    maxY = Math.max(maxY, j);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                sb.append(maze[i][j]);
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
