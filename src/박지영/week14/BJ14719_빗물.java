package 박지영.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14719_빗물 {
    static int H, W;
    static int[] blocks;
    static int[][] map;     // 1 : 블록, 2: 물
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        blocks = new int[W];
        map = new int[H][W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            for (int j = H-Integer.parseInt(st.nextToken()); j < H ; j++) {
                map[j][i] = 1;
            }
        }

        int cnt = 0;
        for (int i = H-1; i >= 0 ; i--) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] ==0 && checkWater(i, j)) {
                    map[i][j] = 2;
                    cnt++;
                }
            }
        }

        System.out.print(cnt);

    }

    private static boolean checkWater(int y, int x) {
        boolean leftPossible = false;
        boolean rightPossible = false;
        // 왼쪽이 막혀있는지 체크
        for (int k = x; k >= 0 ; k--) {
            if (map[y][k] == 1 || map[y][k] == 2) {
                leftPossible = true;
                break;
            }
        }

        // 오른쪽이 막혀있는지 체크
        for (int k = x; k < W; k++) {
            if (map[y][k] == 1 || map[y][k] == 2) {
                rightPossible = true;
                break;
            }
        }

        return leftPossible&&rightPossible;
    }

}
