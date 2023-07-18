package 박지영.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1347_미로만들기 {

    // 우회전 기준
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[101][101];
        char[] arr = br.readLine().toCharArray();

        for (int i = 0; i < 101; i++) {
            Arrays.fill(map[i], '#');
        }

        int dir = 0;
        int y = 50;
        int x = 50;
        map[y][x] = '.';
        int maxY = 50;
        int maxX = 50;
        int minY = 50;
        int minX = 50;
        for(char c : arr) {
            if (dir<0) dir =3;

            switch (c) {
                case 'F':
                    y += dy[dir%4];
                    x += dx[dir%4];
                    maxY = Math.max(maxY, y);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    minX = Math.min(minX, x);
                    map[y][x] = '.';
                    break;
                case 'R':
                    dir++;
                    break;
                case 'L':
                    dir--;
                    break;
            }
        }

        //print
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}