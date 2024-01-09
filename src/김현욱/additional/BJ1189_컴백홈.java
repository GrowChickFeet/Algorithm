package 김현욱.additional;

import java.io.*;
import java.util.StringTokenizer;

public class BJ1189_컴백홈 {
    public static int[][] mv = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static boolean isIn(char[][] maze, int x, int y) {
        int r = maze.length;
        int c = maze[0].length;

        return 0 <= x && x < r && 0 <= y && y < c;
    }

    public static int func(char[][] maze, boolean[][] visited, int x, int y, int count, int k) {
        if (x == 0 && y == maze[0].length - 1) {
            return (count == k ? 1 : 0);
        }

        int result = 0;
        for (int d = 0; d < mv.length; d++) {
            int nx = x + mv[d][0];
            int ny = y + mv[d][1];

            if (isIn(maze, nx, ny) && !visited[nx][ny] && maze[nx][ny] != 'T') {
                visited[nx][ny] = true;
                result += func(maze, visited, nx, ny, count + 1, k);
                visited[nx][ny] = false;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[][] maze = new char[r][c];
        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            maze[i] = br.readLine().toCharArray();
        }
        visited[r - 1][0] = true;
        bw.write(Integer.toString(func(maze, visited, r - 1, 0, 1, k)));

        br.close();
        bw.close();
    }
}
