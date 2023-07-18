package 박지영.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17836_공주님을구해라 {
    static int N, M, T, gramY, gramX;
    static int[][] map, visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static final int MAXTIME = 10001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], MAXTIME);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    gramY = i;
                    gramX = j;
                }
            }
        }

        int findGramTime = findGram();
        int resultTime = Math.min(findPrince(gramY, gramX, findGramTime, true), findPrince(0, 0, 0, false) );
        System.out.print(resultTime == MAXTIME ? "Fail" : resultTime);
    }

    static int findPrince(int y, int x, int cnt, boolean gram) {
        for (int i = 0; i < N; i++) {       // 초기화
            Arrays.fill(visited[i], MAXTIME);
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x, cnt});
        visited[y][x] = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[2] > T) return MAXTIME;

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny >= N || ny < 0 || nx >= M || nx <0 || visited[ny][nx] <= cur[2]+1) continue;
                if (ny == N-1 && nx == M-1) return cur[2]+1;
                if (map[ny][nx] == 0) {
                    visited[ny][nx] = cur[2] + 1;
                    q.offer(new int[] {ny, nx, cur[2]+1});
                } else if (gram) {      // 그람이 있으면
                    visited[ny][nx] = cur[2] + 1;
                    q.offer(new int[] {ny, nx, cur[2]+1});
                }
            }
        }
        return MAXTIME;
    }

    static int findGram() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0});
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[2] > T) return MAXTIME;

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny >= N || ny < 0 || nx >= M || nx <0 || visited[ny][nx] <= cur[2]+1) continue;
                if (ny == gramY && nx == gramX) return cur[2]+1;
                if (map[ny][nx] != 1) {
                    visited[ny][nx] = cur[2] + 1;
                    q.offer(new int[] {ny, nx, cur[2]+1});
                }
            }
        }
        return MAXTIME;
    }
}