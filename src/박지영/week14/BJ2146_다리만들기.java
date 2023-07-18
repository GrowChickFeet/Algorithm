package 박지영.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2146_다리만들기 {
    static int N, result;
    final static int INF = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Node implements Comparable<Node>{
        int y, x, cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cnt, o.cnt);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = INF;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {

                    visited = new boolean[N][N];
                    mark(i, j);
                    result = Math.min(result, findMinDist(i, j));
                }
            }
        }

        System.out.println(result);
    }

    static void mark(int y, int x) {
        Queue<int[] > q = new ArrayDeque<>();
        q.offer(new int[]{y, x});
        map[y][x] = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dir[i][0];
                int nx = cur[1] + dir[i][1];

                if (ny >= N || ny < 0 || nx >= N || nx < 0) continue;

                if (map[ny][nx] == 1) {
                    q.offer(new int[] { ny, nx});
                    map[ny][nx] = -1;
                }
            }
        }
    }

    static int findMinDist(int y, int x) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(y, x, 0));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.cnt >= result) break;

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dir[i][0];
                int nx = cur.x + dir[i][1];

                if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx]) continue;

                if (map[ny][nx] == 1) return cur.cnt;       // 새로운 섬을 발견하면 바로 리턴

                if (map[ny][nx] == -1) q.offer(new Node(ny, nx, 0));        // 기존의 섬이면 큐에 추가
                else q.offer(new Node(ny, nx, cur.cnt + 1));                // 바다면 카운트 증가해서 큐에 추가
                visited[ny][nx] = true;        // 방문처리
            }
        }
        return INF;     // 발견 못하면..
    }
}