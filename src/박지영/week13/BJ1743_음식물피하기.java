package 박지영.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1743_음식물피하기 {
    static int N, M, K;
    static boolean[][] visited;
    static boolean[][] map;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N+1][M+1];
        visited = new boolean[N+1][M+1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = true;
        }

        int result = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < M+1; j++) {
                if (map[i][j] && !visited[i][j]) result = Math.max(result, bfs(i, j));
            }
        }

        System.out.print(result);
    }

    public static int bfs(int y, int x){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;

        int cnt = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny > N || ny <= 0 || nx > M || nx <= 0 || visited[ny][nx]) continue;

                if (map[ny][nx]) {
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }

            }
        }

        return cnt;
    }
}
