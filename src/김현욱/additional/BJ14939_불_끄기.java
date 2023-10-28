package 김현욱.additional;

import java.io.*;
import java.util.Arrays;

public class BJ14939_불_끄기 {
    static final int SIZE = 10;
    static boolean[][] origin = new boolean[SIZE][SIZE];
    static boolean[][] temp = new boolean[SIZE][SIZE];
    static int[][] mv = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int tempCount = 0;

    static void init() {
        tempCount = 0;
        for (int i = 0; i < SIZE; i++) {
            temp[i] = origin[i].clone();
        }
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < SIZE && 0 <= y && y < SIZE;
    }

    static void click(boolean[][] maze, int x, int y) {
        for (int d = 0; d < mv.length; d++) {
            int nx = x + mv[d][0];
            int ny = y + mv[d][1];

            if (isIn(nx, ny)) {
                maze[nx][ny] = !maze[nx][ny];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < SIZE; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < SIZE; j++) {
                origin[i][j] = line[j] == '#' ? false : true;
            }
        }

        int result = Integer.MAX_VALUE;
        //첫번째 줄을 변경시키기 위한 스위치 경우의 수
        for (int c = 0; c < (1 << 10); c++) {
            init();

            //첫번째 줄 변경
            for (int j = 0; j < SIZE; j++) {
                if ((c & (1 << j)) > 0) {
                    tempCount++;
                    click(temp, 0, j);
                }
            }

            //둘째줄부터 작업 시작
            for (int i = 1; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (temp[i - 1][j]) {
                        tempCount++;
                        click(temp, i, j);
                    }
                }
            }

            boolean flag = true;
            for (int j = 0; j < SIZE; j++) {
                if (temp[SIZE - 1][j]) {
                    flag = false;
                    break;
                }
            }

            if (flag) result = Math.min(result, tempCount);
        }
        bw.write(Integer.toString(result));

        br.close();
        bw.close();
    }
}
