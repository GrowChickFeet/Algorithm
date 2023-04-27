package 박지영.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4936_섬의개수 {
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};

    static int w, h;
    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        while (w != 0 && h!=0) {
            // 맵 생성
            int count = 0;
            map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1) {
                        count++;
                        bfs(i, j);
                    }
                }
            }

            sb.append(count).append("\n");
            // 다음 값 입력 받기
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb);
    }

    static void bfs(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(y, x));
        map[y][x] = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 8; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny >= h || ny < 0 || nx >= w || nx < 0) continue;
                if (map[ny][nx] == 1) {
                    map[ny][nx] = 0;
                    q.offer(new Node(ny, nx));
                }
            }
        }
    }
}
