package 박지영.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2665_미로만들기 {
    static int N, result;
    static int[][] map;
    static int[][] visited;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        dfs(0, 0, 0);
        System.out.print(result);
    }

    static void dfs(int y, int x, int count) {      // count: 지나온 검은방의 수
        if (y==N-1 && x==N-1) {
            result = Math.min(count, result);
            return;
        }

        visited[y][x] = count;      // 해당 지점까지의 최소 가는 검은방의 개수로 update

        for (int i = 0; i < 4; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
            if (ny>=N || ny<0 || nx>=N||nx<0) continue;     // 방문 못하는 곳

            if (visited[ny][nx] <= count) continue;      // 더 최소로 방문한적 있는 곳

            if (map[ny][nx] == 0) dfs(ny, nx, count+1);     // 검은방
            else dfs(ny, nx, count);        // 흰방
        }
    }
}
