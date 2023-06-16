package 박지영.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ23288_주사위_굴리기_2 {
    static int[] dice = {2, 4, 1, 3, 5, 6};
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(move());
    }

    // 주사위 이동
    static int move() {
        int totalScore = 0;
        int dir = 0;        // 0: 동, 1:남, 2: 서: 3: 북
        int y = 0;
        int x = 0;
        for (int i = 0; i < K; i++) {
            if (dir < 0) dir = 3;
            else if (dir == 4) dir = 0;

            // 이동방향에 칸이 없다면?
            switch (dir) {
                case 0: // 동
                    if (x+1 >= M) dir = 2;
                    break;
                case 1: // 남
                    if (y+1 >= N) dir = 3;
                    break;
                case 2: // 서
                    if (x-1 <0) dir = 0;
                    break;
                case 3: // 북
                    if (y-1 <0) dir = 1;
                    break;
            }

            // 주사위 전개도 변화
            switch (dir) {
                case 0: // 동
                    int tmp = dice[1];
                    dice[1] = dice[5];
                    dice[5] = dice[3];
                    dice[3] = dice[2];
                    dice[2] = tmp;
                    x++;
                    break;
                case 1: // 남
                    tmp = dice[0];
                    dice[0] = dice[5];
                    dice[5] = dice[4];
                    dice[4] = dice[2];
                    dice[2] = tmp;

                    y++;
                    break;
                case 2: // 서
                    tmp = dice[1];
                    dice[1] = dice[2];
                    dice[2] = dice[3];
                    dice[3] = dice[5];
                    dice[5] = tmp;
                    x--;
                    break;
                case 3: // 북
                    tmp = dice[0];
                    dice[0] = dice[2];
                    dice[2] = dice[4];
                    dice[4] = dice[5];
                    dice[5] = tmp;
                    y--;
                    break;
            }
            // 점수 획득
            totalScore += score(y, x);

            // 이동방향 결정
            int A = dice[5];
            int B = map[y][x];
            if (A > B) dir++;
            else if(A < B) dir--;
        }

        return totalScore;
    }


    // 점수 계산 bfs
    static int score(int y, int x) {
        int count = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x});

        visited = new boolean[N][M];
        visited[y][x] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            count++;
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny >= N || ny < 0 || nx >= M || nx < 0 || visited[ny][nx]) continue;

                if (map[ny][nx] == map[y][x]) {
                    q.offer(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
        return count*map[y][x];
    }
}
