package 남현실.additional;

/*
https://www.acmicpc.net/problem/23288
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
N*M 지도
- 방향
    - 오른쪽: 동쪽
    - 위쪽: 북쪽
    - 가장 왼쪽에 있는 칸의 좌표는 (1,1) 가장 오른쪽은 (N, M)
- 좌표: r, c
    - r: 북쪽으로 떨어진 칸의 개수
    - c: 왼쪽 위에 있는 칸의 좌표
- 주사위
    -   2
      4 1 3
        5
        6
    - 윗 면: 1
    - 동쪽: 3
    - 좌표 (1,1)
- 주사위 이동
    - 처음 이동방향: 동쪽
    - 이동 방향으로 한 칸 굴러감
    - 이동 방향에 칸이없으면, 이동 방향을 반대로 한 다음 한칸 굴러감
    - 주사위가 도착한 칸 (x,y)에 대한 점수 획득
    - 주사위 아랫면에 있는 정수 A 와 주사위 칸 (x,y)에 있는 정수 B를 비교해서 이동방향 변화
        - A > B: 90시계 방향 회전
        - A < B: 90도 반시계 방향 회전
        - A = B: 변화 없음
    - 칸 (x,y) 점수
        - B: (x,y)에 있는 정수
        - C: 동서남북 방향으로 연속으로 이동할 수 있는 칸의 수 (전부 B로 이루어져있어야)
        - B*C

 - 주사위의 이동횟수 K가 주어졌을 때 각 이동에서 획득하는 점수의 합?
 */
public class BJ23288_주사위_굴리기2 {
    static int N, M, K;
    static int[][] map;

    static Dice dice;

    static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Dice {
        HashMap<String, Integer> info;
        String dir = "E";
        int x, y;
        static final HashMap<String, int[]> moveInfo = new HashMap<String, int[]>() {{
            put("E", new int[]{0, 1});
            put("W", new int[]{0, -1});
            put("S", new int[]{1, 0});
            put("N", new int[]{-1, 0});
        }};

        public Dice() {
            info = new HashMap<>() {{
                put("U", 1);
                put("D", 6);
                put("E", 3);
                put("W", 4);
                put("S", 5);
                put("N", 2);
            }};
        }

        public void roll() { // move
            if (dir.equals("W")) {
                rollW();
            } else if (dir.equals("E")) {
                rollE();
            } else if (dir.equals("S")) {
                rollS();
            } else if (dir.equals("N")) {
                rollN();
            }
        }

        public void rollW() {
            HashMap<String, Integer> clone = cloneInfo();
            info.put("D", clone.get("W"));
            info.put("E", clone.get("D"));
            info.put("U", clone.get("E"));
            info.put("W", clone.get("U"));
        }

        public void rollE() {
            HashMap<String, Integer> clone = cloneInfo();
            info.put("D", clone.get("E"));
            info.put("W", clone.get("D"));
            info.put("U", clone.get("W"));
            info.put("E", clone.get("U"));
        }

        public void rollS() {
            HashMap<String, Integer> clone = cloneInfo();
            info.put("D", clone.get("S"));
            info.put("N", clone.get("D"));
            info.put("U", clone.get("N"));
            info.put("S", clone.get("U"));
        }

        public void rollN() {
            HashMap<String, Integer> clone = cloneInfo();
            info.put("D", clone.get("N"));
            info.put("S", clone.get("D"));
            info.put("U", clone.get("S"));
            info.put("N", clone.get("U"));
        }

        public void rotateDir45() { // clock
            if (dir.equals("E")) {
                dir = "S";
            } else if (dir.equals("S")) {
                dir = "W";
            } else if (dir.equals("W")) {
                dir = "N";
            } else if (dir.equals("N")) {
                dir = "E";
            }
        }

        public void rotateDir45Reverse() { // clock reverse
            if (dir.equals("E")) {
                dir = "N";
            } else if (dir.equals("N")) {
                dir = "W";
            } else if (dir.equals("W")) {
                dir = "S";
            } else if (dir.equals("S")) {
                dir = "E";
            }
        }

        public void rotateDir180() {
            HashMap<String, Integer> clone = cloneInfo();
            if (dir.equals("W")) {
                dir = "E";
            } else if (dir.equals("N")) {
                dir = "S";
            } else if (dir.equals("E")) {
                dir = "W";
            } else if (dir.equals("S")) {
                dir = "N";
            }
        }


        public void move() {
            int nx, ny;
            int[] offset = moveInfo.get(dir);
            nx = x + offset[0];
            ny = y + offset[1];
            if (checkoutOfMap(nx, ny)) { // out of map -> change dir
                rotateDir180();
                offset = moveInfo.get(dir);
                nx = x + offset[0];
                ny = y + offset[1];
            }
            // move idx
            x = nx;
            y = ny;

            // roll dice
            roll();
        }

        public HashMap<String, Integer> cloneInfo() {
            return (HashMap<String, Integer>) info.clone();
        }

        public void showInfo() {
            System.out.println("up, down: " + info.get("U") + ", " + info.get("D"));
            System.out.println("East, South, West, North: " + info.get("E") + ", " + info.get("S") + ", " + info.get("W") + ", " + info.get("N"));
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        dice = new Dice();

        int totalScore = 0;
        for (int i = 0; i < K; i++) {
            dice.move();
            totalScore += getScore();
            rotateDice();
        }
        System.out.println(totalScore);
    }

    static int getScore() {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{dice.x, dice.y});
        visited[dice.x][dice.y] = true;

        int tgNum = map[dice.x][dice.y];

        int count = 1; // start count
        int[] cur;
        int cx, cy, nx, ny;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cx = cur[0];
            cy = cur[1];
            for (int[] move : moves) {
                nx = cx + move[0];
                ny = cy + move[1];
                if (checkoutOfMap(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (!(map[nx][ny] == tgNum)) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
                count += 1;
            }
        }

        return (tgNum*count);
    }

    static void rotateDice() {
        int numA = dice.info.get("D");
        int numB = map[dice.x][dice.y];

        if (numA > numB) {
            dice.rotateDir45();
        } else if (numA < numB) {
            dice.rotateDir45Reverse();
        }
    }

    static boolean checkoutOfMap(int x, int y) {
        return (x < 0 || x >= N || y < 0 || y >= M);
    }

    static void showMap() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
