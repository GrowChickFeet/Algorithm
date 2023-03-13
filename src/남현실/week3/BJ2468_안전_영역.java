package 남현실.week3;

/*
https://www.acmicpc.net/problem/2468
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2468_안전_영역 {
    static int N;
    static boolean[][] visited;
    static int[][] map;
    static int vCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] temp;
        map = new int[N][N];
        int min = Integer.MAX_VALUE, max = 0;


        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int zoneCnt, maxZoneCnt = 1; // depth가 min보다 작으면 전부 선택
        for (int d = min; d < max; d++) { // max이먄 안전지대 없음
            zoneCnt = 0;
            visited = new boolean[N][N];

            // visited 안된 안전지대 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    vCnt = 0;
                    explore(i, j, d);

                    if (vCnt > 0) {
                        zoneCnt++;
                    }
                }
            }
            maxZoneCnt = Math.max(maxZoneCnt, zoneCnt);
        }
        System.out.println(maxZoneCnt);

        br.close();
    }

    static void explore(int x, int y, int depth) {
        visited[x][y] = true;

        if (map[x][y] <= depth) {
            return;
        }

        vCnt++;

        int nx, ny;
        for (int[] move : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
            nx = x + move[0];
            ny = y + move[1];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            if (visited[nx][ny]) {
                continue;
            }
            explore(nx, ny, depth);
        }
    }
}
