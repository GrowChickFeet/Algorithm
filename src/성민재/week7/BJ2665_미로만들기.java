package 성민재.week7;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2665_미로만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int[][] map, dirction = {{0,1}, {1,0}, {0,-1}, {-1,0}}; //우 하 좌 상
    static int N, black_cnt, ans;
    static int[][] change_room;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        black_cnt = 0;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        ans = Integer.MAX_VALUE;
        change_room = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(change_room[i], Integer.MAX_VALUE);
        }

        dfs(new Node(0, 0,0));
        System.out.println(ans);
    }

    private static void dfs(Node n) {
        if(n.row == N-1 && n.col == N-1){ //끝방에 도착했을 경우
            ans = Math.min(ans, n.change); // 흰방으로 바꾼 것이 최소인 경우 갱신
            return;
        }

        for (int k = 0; k < 4; k++) {
            Node nx = new Node(n.row + dirction[k][0], n.col + dirction[k][1], n.change);
            if(!isIn(nx)) continue; // 범위 안인지 확인
            if(change_room[nx.row][nx.col] <= nx.change) continue;

            if(map[nx.row][nx.col] == 0) { //검은방인 경우 진입
                nx.change++;
            }

            change_room[n.row][n.col] = n.change;
            dfs(nx);
        }
    }

    private static boolean isIn(Node nx) {
        return nx.row >= 0 && nx.row < N && nx.col >= 0 && nx.col < N;
    }

    static class Node{
        int row, col, change;

        public Node(int row, int col, int change) {
            this.row = row;
            this.col = col;
            this.change = change;
        }
    }
}
