package 박지영.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BJ2151_거울설치 {
    static int INF = Integer.MAX_VALUE;
    static int N, result = Integer.MAX_VALUE;
    static char[][] map;
    static int[][] dir = {{-1, 0},{0, 1}, {1, 0}, {0, -1} };
    static int[][][] visited;
    static ArrayList<int[]> door = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    visited[i][j][k] = INF;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == '#') {
                    door.add(new int[]{i, j});
                }
            }
        }

        bfs();

        System.out.print(result);
    }

    // int[] {y, x, 거울의 개수, dir}
    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{door.get(0)[0], door.get(0)[1], 0, 0});
        queue.offer(new int[]{door.get(0)[0], door.get(0)[1], 0, 1});
        queue.offer(new int[]{door.get(0)[0], door.get(0)[1], 0, 2});
        queue.offer(new int[]{door.get(0)[0], door.get(0)[1], 0, 3});
        visited[door.get(0)[0]][door.get(0)[1]][0] = 0;
        visited[door.get(0)[0]][door.get(0)[1]][1] = 0;
        visited[door.get(0)[0]][door.get(0)[1]][2] = 0;
        visited[door.get(0)[0]][door.get(0)[1]][3] = 0;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == door.get(1)[0] && cur[1] == door.get(1)[1]) {
                result = Math.min(result, cur[2]);
                continue;
            }
            if (map[cur[0]][cur[1]] == '.' || map[cur[0]][cur[1]] == '#') {       // 거울이 아님
                int ny = cur[0] + dir[cur[3]][0];
                int nx = cur[1] + dir[cur[3]][1];

                if (possible(ny, nx, cur[3], cur[2])) {
                    queue.offer(new int[]{ny, nx, cur[2], cur[3]});
                    visited[ny][nx][cur[3]] = cur[2];
                }
            } else {        // 거울임

                int tmpDir = (cur[3] + 1) % 4;
                int ny = cur[0] + dir[tmpDir][0];
                int nx = cur[1] + dir[tmpDir][1];

                if(possible(ny, nx, tmpDir, cur[2]+1)) {
                    queue.offer(new int[]{ny, nx, cur[2]+1, tmpDir});
                    visited[ny][nx][tmpDir] = cur[2]+1;
                }

                tmpDir = cur[3] - 1 < 0 ? 3 : cur[3] - 1;

                ny = cur[0] + dir[tmpDir][0];
                nx = cur[1] + dir[tmpDir][1];

                if(possible(ny, nx, tmpDir, cur[2]+1)) {
                    queue.offer(new int[]{ny, nx, cur[2]+1, tmpDir});
                    visited[ny][nx][tmpDir] = cur[2]+1;
                }

                // 거울 설치 안함
                tmpDir = cur[3];

                ny = cur[0] + dir[tmpDir][0];
                nx = cur[1] + dir[tmpDir][1];

                if(possible(ny, nx, tmpDir, cur[2])) {
                    queue.offer(new int[]{ny, nx, cur[2], tmpDir});
                    visited[ny][nx][tmpDir] = cur[2];
                }

            }
        }

    }
    static boolean possible(int y, int x, int dir, int mirrorCount) {
        return y < N && y >= 0 && x < N && x >= 0 && visited[y][x][dir] > mirrorCount && map[y][x] != '*';
    }

}