package 남현실.week7;

/*
https://www.acmicpc.net/problem/4963
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
[입력]
- 여러개의 테스트케이스
- 지도의 w, h (1 <= w,h <= 50)
- 마지막 줄은 0 2개 주어짐

[조건]
- 지도
    - 1: 땅
    - 0: 바다
- 사방의 가로,세로,대각선 맞닿는 곳에 섬이 있으면 하나의 섬으로 처리

[출력]
- (각 테스트 케이스에 대한) 섬의 개수

 */
public class BJ4963_섬의_개수 {
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp;

        while (true) {
            int w, h;
            temp = br.readLine().split(" ");
            w = Integer.parseInt(temp[0]);
            h = Integer.parseInt(temp[1]);

            if (w == 0 && h == 0) {
                break;
            }

            int[][] map = new int[h][w];
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                temp = br.readLine().split(" ");
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(temp[j]);
                }
            }

            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 0) {
                        continue;
                    }
                    if (visited[i][j]) {
                        continue;
                    }
                    explore(new int[]{i, j}, map, visited);
                    count += 1;
                }
            }
            System.out.println(count);

        }
    }

    private static void explore(int[] start, int[][] map, boolean[][] visited) {
        int h = map.length;
        int w = map[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);

        int[] cur;
        int curX, curY;
        int nextX, nextY;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            curX = cur[0];
            curY = cur[1];

            if (visited[curX][curY]) {
                continue;
            }

            visited[curX][curY] = true;

            for (int[] move : new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}) {
                nextX = curX + move[0];
                nextY = curY + move[1];
                if (nextX < 0 || nextX >= h || nextY < 0 || nextY >= w) {
                    continue;
                }
                if (map[nextX][nextY] == 0) {
                    continue;
                }

                queue.offer(new int[]{nextX, nextY});
            }
        }
    }
}
