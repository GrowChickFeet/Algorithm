package 남현실.week4;

/*
https://www.acmicpc.net/problem/2573
 */

/*
[입력]
- N, M: 이차원배열 정보 (행, 열) (3 <= N,M <= 300)
- 배열 원소값 (0 <= x <= 10)
- 배열 빙산 칸의 개수 (10,000 이하)
- 배열 첫번째 마지막 행,열 0으로 채워짐

[정보]
- 1년마다 빙하 주위의 동서남북 0의 개수따라 감소
- 빙하 분리되었을 때까지 (그래프 2개 이상 발생)

[출력]
빙산이 분리되는 최초의 시간
    - 다 녹을 때까지 두 덩어리 이상 분리되지 않으면: 0

[풀이]
-


 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2573_빙산 {
    static int N, M;
    static LinkedList<int[]> glaciers;
    static int[][] map;
    static boolean[][] visited;
    static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        // 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        glaciers = new LinkedList<>();
        map = new int[N][M];

        int maxH = 0;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
                if (map[i][j] == 0) {
                    continue;
                }
                glaciers.add(new int[]{i, j});
                maxH = Math.max(maxH, map[i][j]);
            }
        }

        int year = 0;
        int gCnt = 0;
        while (!glaciers.isEmpty()) {
            visited = new boolean[N][M];
            gCnt = graphCnt();
            if (gCnt != 1) {
                break;
            }
            melt();
            year++;
        }
        int result = (gCnt == 0 || gCnt == 1) ? 0 : year;
        System.out.println(result);
    }

    static void melt() {
        int zCnt;
        int x, y;
        ArrayList<int[]> rms = new ArrayList<>();

        for (int[] glacier : glaciers) {
            x = glacier[0];
            y = glacier[1];

            zCnt = zeroCnt(new int[]{x, y});
            if (map[x][y] <= zCnt) {
                rms.add(glacier);
                continue;
            }
            map[x][y] -= zCnt;
        }
        for (int[] rm : rms) {
            map[rm[0]][rm[1]] = 0;
            glaciers.remove(rm);
        }

    }

    static int zeroCnt(int[] cur) {
        int zeroCnt = 0;
        int nx, ny;
        for (int[] move : moves) {
            nx = cur[0] + move[0];
            ny = cur[1] + move[1];

            if (!checkNextValid(nx, ny)) {
                continue;
            }
            if (map[nx][ny] != 0) {
                continue;
            }
            zeroCnt++;
        }
        return zeroCnt;
    }

    static int graphCnt() {
        int cnt = 0;
        for (int[] glacier : glaciers) {
            if (visited[glacier[0]][glacier[1]]) {
                continue;
            }
            exploreGraph(glacier);
            cnt++;
        }
        return cnt;
    }

    static void exploreGraph(int[] start) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start[0]][start[1]] = true;

        int[] cur;
        int nx, ny;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            for (int[] move : moves) {
                nx = cur[0] + move[0];
                ny = cur[1] + move[1];

                if (!checkNextValid(nx, ny)) {
                    continue;
                }
                if (map[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    static boolean checkNextValid(int nx, int ny) {
        return !(nx < 0 || nx >= N || ny < 0 || ny >= M);
    }
}