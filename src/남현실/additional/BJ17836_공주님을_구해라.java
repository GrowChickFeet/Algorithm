package 남현실.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ17836_공주님을_구해라 {
    static int[][] moves = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    static class Node {
        int x, y, cnt;
        boolean knife;

        public Node(int x, int y, int cnt, boolean knife) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.knife = knife;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cnt=" + cnt +
                    ", knife=" + knife +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int T = Integer.parseInt(temp[2]);

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        queue.offer(new Node(0, 0, 0, false));
        visited[0][0] = true;


        int min = Integer.MAX_VALUE;
        int knifeMin = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;

            if (cur.cnt > T) { // 시간 초과면 fail 출력하고 끝냄
                break;
            }

            if (cur.knife && knifeMin != Integer.MAX_VALUE) { // knife min이 이미 구해졌는데 또 구하려하면 넘기기
                continue;
            }

            if (x == N - 1 && y == M - 1) { // 공주님에게 도착하면 break;
                min = cur.cnt;
                break;
            }

            int nx, ny;
            int ncnt = cur.cnt + 1;
            boolean nknife = cur.knife;
            for (int[] move : moves) {
                nx = x + move[0];
                ny = y + move[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) { // out of map
                    continue;
                }

                if (visited[nx][ny]) { // 방문했으면 넘기기
                    continue;
                }


                if (map[nx][ny] == 1) { // 벽이면 넘긱기
                    continue;
                }

                if (!cur.knife && map[nx][ny] == 2) { // 칼이 있으면 바로 공주에게 가는 길 구하기
                    nknife = true;
                    knifeMin = ncnt + (N-nx-1) + (M-ny-1); // knife 최솟값 바로 구하기
                }

                // 추가
                queue.offer(new Node(nx, ny, ncnt, nknife));
                visited[nx][ny] = true;
            }
        }
        int result = Math.min(min, knifeMin); // min값 구하기
        if(result > T) { // 시간 초과거나 접근이 불가능하면 fail 출력하고 끝냄
            System.out.println("Fail");
            return;
        }
        System.out.println(result);
    }
}
