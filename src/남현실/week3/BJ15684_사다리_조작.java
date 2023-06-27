package 남현실.week3;

/*
https://www.acmicpc.net/problem/15684
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ15684_사다리_조작 {
    static int N, H, M;
    static boolean[][] ladders; // true: 사다리있음, false: 일반

    static ArrayList<Node> nodes;
    static boolean isFail;
    static final int max = 3;
    static int minH = -1;

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        // get input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        H = Integer.parseInt(temp[1]);
        M = Integer.parseInt(temp[2]);

        ladders = new boolean[M][N];

        int x, y;
        for (int i = 0; i < H; i++) {
            temp = br.readLine().split(" ");
            x = Integer.parseInt(temp[0]) - 1;
            y = Integer.parseInt(temp[1]) - 1;
            ladders[x][y] = true;
        }

        // get node
        nodes = new ArrayList();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (ladders[i][j] || (j - 1 >= 0 && ladders[i][j - 1]) || (j + 1 < N && ladders[i][j + 1])) continue;
                nodes.add(new Node(i, j));
            }
        }

        subset(0, 0);

        System.out.println(minH);

        br.close();
    }

    static void subset(int idx, int cnt) {
        if (minH != -1 && minH <= cnt) {
            return;
        }

        isFail = false;
        for (int i = 0; i < N; i++) {
            if (isFail) { // 안되는 경우가 하나라도 있으면 멈춤
                break;
            }
            explore(0, i, i);
        }

        if (!isFail) {
            if (minH == -1) {
                minH = cnt;
            } else {
                minH = Math.min(cnt, minH);
            }
        }

        if (cnt >= max || idx == nodes.size()) {
            // minH이 이 업데이트되면 더이상 검사하지 않음 (시간 초과 방지) <- subset을 개수가 오름차순이기 떄문에 되는 경우가 생기면 바로 결과값을 리턴해도됨
            return;
        }

        Node node = nodes.get(idx);
        int x = node.x;
        int y = node.y;

        if (!((y - 1 >= 0 && ladders[x][y - 1]) || (y + 1 < N && ladders[x][y + 1]))) {
            ladders[x][y] = true;
            subset(idx + 1, cnt + 1);
            ladders[x][y] = false;
        }
        subset(idx + 1, cnt);
    }

    static void explore(int x, int y, int tg) {
        if (isFail) { // 실패한 경우가 있으면 즉시 멈춤
            return;
        }

        if (x == M) { // 끝 도달
            if (y != tg) { // 결과가 처음과 같은 값이 아니면 이 부분집합은 검사 취소
                isFail = true;
            }
            return;
        }

        //사다리가 없는 경우
        int nx = x + 1;
        int ny = y;

        // 방문안한 사다리가 있는 경우
        if (y > 0 && ladders[x][y - 1]) { // 사다리가 왼쪽에 있는 경우
            ny = y - 1;
        } else if (y < N && ladders[x][y]) { // 사다리가 오른쪽에 있는 경우
            ny = y + 1;
        }

        explore(nx, ny, tg);
    }
}