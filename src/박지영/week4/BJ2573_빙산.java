package 박지영.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2573_빙산 {
    static int N, M;
    static int[][] arr;
    static int[][] tmpArr;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static class Ice {
        int y;
        int x;
        int height;

        public Ice(int y, int x, int height) {
            this.y = y;
            this.x = x;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // -------- 입력 받기 ------- //

        int day = 0;
        while(true) {

            visited = new boolean[N][M];
            int iceCount = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0 && !visited[i][j]) {
                        count(i, j);
                        iceCount++;
                    }
                }
            }

            if (iceCount >= 2) break;
            if (iceCount == 0) {
                day=0;
                break;
            }
            day++;
            melt();
            sum();
        }
        System.out.print(day);      // 결과 출력
    }

    static void sum() {        // tmpArr에 저장한 녹일 개수에 실제 빙산 배열에 더하여서 값 출력하기
        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < M-1; j++) {
                arr[i][j] = (arr[i][j] + tmpArr[i][j] > 0 ? arr[i][j] + tmpArr[i][j] : 0);      // 0이하로 내려가게 되면 0으로 출력
            }
        }
    }

    static void melt() {        // 빙산 녹이기
        tmpArr = new int[N][M];
        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < M-1; j++) {
                if (arr[i][j] == 0) continue;   // 녹일 부분이 바다면 다음으로

                for (int k = 0; k < 4; k++) {   // 상하좌우로 0의 개수를 세서 tmpArr에 녹일 개수 저장
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (arr[ny][nx]==0) {
                        tmpArr[i][j]--;
                    }
                }
            }
        }
    }
    // 빙산 조각 수 세기
    static void count(int y, int x) {
        Queue<Ice> queue = new ArrayDeque<>();
        queue.offer(new Ice(y, x, arr[y][x]));
        visited[y][x] = true;
        while(!queue.isEmpty()) {
            Ice current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = current.y+dy[i];
                int nx = current.x+dx[i];
                if (ny >= N-1 || ny < 1 || nx >= M-1 || nx < 1) continue;
                if (!visited[ny][nx] && arr[ny][nx] != 0) {     // 방문하지 않고 빙산이 있으면
                    visited[ny][nx] = true;
                    queue.offer(new Ice(ny, nx, arr[ny][nx]));  // 방문처리하고 큐에 담기
                }
            }
        }

    }
}
