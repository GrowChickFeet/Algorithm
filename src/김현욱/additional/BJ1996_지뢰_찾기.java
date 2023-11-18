package 김현욱.additional;

import java.io.*;

public class BJ1996_지뢰_찾기 {
    static int[][] mv = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    static boolean isIn(int n, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        char[][] maze = new char[n][n];
        for (int i = 0; i < n; i++) {
            maze[i] = br.readLine().toCharArray();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char result;
                if (maze[i][j] != '.') {
                    result = '*';
                } else {
                    int count = 0;
                    for (int d = 0; d < mv.length; d++) {
                        int nx = i + mv[d][0];
                        int ny = j + mv[d][1];

                        if (isIn(n, nx, ny) && maze[nx][ny] != '.') count += (maze[nx][ny] - '0');
                    }
                    result = count >= 10 ? 'M' : (char) (count + '0');
                }
                sb.append(result);
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
