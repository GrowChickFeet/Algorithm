package 박지영.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BFS를 이용해서 가장 먼 거리를 계속해서 update하면서 풀이
 */
public class BJ2589_보물섬 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) == 'L' ? 1 : 0;
            }
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {       // 모든 Land에 대하여 탐색
                    visited = new boolean[N][M];
                    result = Math.max(result, findMaxLand(i, j));       // 해당 지점에서 가장 먼 거리 update
                }
            }
        }

        System.out.print(result);

    }

    static int findMaxLand(int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x, 0});
        visited[y][x] = true;
        int maxDis = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            maxDis = Math.max(maxDis, cur[2]);      // 가장 먼 거리 저장

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny >= N || ny < 0 || nx >= M || nx < 0) continue;

                if (visited[ny][nx] || map[ny][nx] == 0) continue;

                q.offer(new int[]{ny, nx, cur[2]+1});
                visited[ny][nx] = true;
            }
        }
        return maxDis;
    }
}
