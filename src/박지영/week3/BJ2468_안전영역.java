package 박지영.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2468_안전영역 {
    static int N, maxHigh;
    static boolean possible;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static class Vertex {
        int y;
        int x;

        public Vertex(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                maxHigh = Math.max(arr[i][j], maxHigh);     // 입력받으면서 가장 높은 지점을 구함
            }
        }

        visited = new boolean[N][N];
        int result = 0;
        for (int i = 0; i <= maxHigh; i++) {        // 물의 높이 증가
            for (int j = 0; j < N; j++) {           // 방문처리 초기화
                Arrays.fill(visited[j], false);
            }
            int count = 0;      // count : 잠기지 않은 개수
            for (int j = 0; j < N; j++) {               // 모든 곳을 bfs탐색
                for (int k = 0; k < N; k++) {
                    possible = true;        // 잠기지 않고 방문한 적이 없는 곳을 탐색
                    bfs(j, k, i);
                    if (possible) {
                        count++;
                    }
                }
            }
            result = Math.max(result, count);
        }
        System.out.println(result);
    }

    static void bfs(int y, int x, int depth) {      // 잠기지 않고 방문한적이 없는 곳만을 탐색함
        if (arr[y][x] <= depth || visited[y][x]) {  // 잠기거나 이미 갔던 곳이거나 잠겨서 못가면
            possible = false;       // 불가능 처리
            return;
        }

        visited[y][x] = true;
        Queue<Vertex> q = new ArrayDeque<>();
        q.offer(new Vertex(y, x));
        while (!q.isEmpty()) {
            Vertex current = q.poll();
            for (int i = 0; i < 4; i++) {       // 상하좌우 탐색
                int ny = current.y + dy[i];
                int nx = current.x + dx[i];
                if (ny >= N || ny < 0 || nx >= N || nx < 0) continue;        // 범위 벗어나서 못가는곳
                if (!visited[ny][nx] && arr[ny][nx] > depth) {      // 방문한적이 없거나 잠기지 않은 경우에만
                    q.offer(new Vertex(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }
    }
}
