package 남현실.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


/*
https://www.acmicpc.net/problem/2151
 */



public class BJ2151_거울_설치 {
    static String[][] map;
    static int N;
    static int[][] moves = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0} // 동남서북
    };

    static class Index {
        int x, y;

        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        Index idx;
        int count;
        int dir;

        public Node(Index idx, int count, int direction) {
            this.idx = idx;
            this.count = count;
            this.dir = direction;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(count, o.count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new String[N][N];
        Index sDoor = null, eDoor = null;
        int doorCnt = 0;
        String[] temp;

        int[][][] visited = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j];
                if (map[i][j].equals("#")) {
                    if (doorCnt == 0) {
                        sDoor = new Index(i, j);
                    } else if (doorCnt == 1) {
                        eDoor = new Index(i, j);
                    }
                    doorCnt += 1;
                    map[i][j] = "*";
                }
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int x, y, nx, ny, ndir;
        for (int dir = 0; dir < 4; dir++) {
            x = sDoor.x;
            y = sDoor.y;
            visited[x][y][dir] = 0;

            nx = x + moves[dir][0];
            ny = y + moves[dir][1];
            if (!isIn(nx, ny)) {
                continue;
            }
            if(isBlock(nx, ny)) {
                if (nx == eDoor.x && ny == eDoor.y) {
                    System.out.println(0);
                    return;
                }
                continue;
            }
            // 직진 시 ('!'시 직진할 경우도 포함)
            pq.offer(new Node(new Index(nx, ny), 0, dir));
            visited[nx][ny][dir] = 0;

            if (map[nx][ny].equals("!")) { // 거울이 있어서 꺽일 경우 양 옆 90도
                ndir = (dir + 1) % 4;
                if (visited[nx][ny][ndir] > visited[x][y][dir] + 1) { // 현재 거울 수가 전 위치에서 막 도착해서 카운트가 올라간 것보다 크면 작은 값으로 갱신
                    visited[nx][ny][ndir] = 1;
                    pq.offer(new Node(new Index(nx, ny), 1, ndir));
                }

                ndir = (dir + 4 - 1) % 4;
                if (visited[nx][ny][ndir] > visited[x][y][dir] + 1) { // 현재 거울 수가 전 위치에서 막 도착해서 카운트가 올라간 것보다 크면 작은 값으로 갱신
                    visited[nx][ny][ndir] = 1;
                    pq.offer(new Node(new Index(nx, ny), 1, ndir));
                }
            }
        }

        int result = Integer.MAX_VALUE;

        int count, dir;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            x = node.idx.x;
            y = node.idx.y;
            count = node.count;
            dir = node.dir;

            nx = x + moves[dir][0];
            ny = y + moves[dir][1];

            if (!isIn(nx, ny)) {
                continue;
            }
            if (isBlock(nx, ny)) {
                if (nx == eDoor.x && ny == eDoor.y) {
                    result = Math.min(result, count);
                }
                continue;
            }

            // 직진 시 ('!'시 직진할 경우도 포함)
            if (visited[nx][ny][dir] > visited[x][y][dir]) {
                visited[nx][ny][dir] = visited[x][y][dir];
                pq.offer(new Node(new Index(nx, ny), count, dir));
            }
            // 거울이 있어서 꺽일 경우 양 옆 90도
            if (map[nx][ny].equals("!")) {
                ndir = (dir + 1) % 4;
                if (visited[nx][ny][ndir] > visited[x][y][dir] + 1) { // 현재 거울 수가 전 위치에서 막 도착해서 카운트가 올라간 것보다 크면 작은 값으로 갱신
                    visited[nx][ny][ndir] = visited[x][y][dir] + 1;
                    pq.offer(new Node(new Index(nx, ny), count + 1, ndir));
                }

                ndir = (dir + 4 - 1) % 4;
                if (visited[nx][ny][ndir] > visited[x][y][dir] + 1) { // 현재 거울 수가 전 위치에서 막 도착해서 카운트가 올라간 것보다 크면 작은 값으로 갱신
                    visited[nx][ny][ndir] = visited[x][y][dir] + 1;
                    pq.offer(new Node(new Index(nx, ny), count + 1, ndir));
                }
            }
        }
        System.out.println(result);
        br.close();

    }

    public static boolean isIn(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }

    public static boolean isBlock(int x, int y) {
        return map[x][y].equals("*");
    }
}
