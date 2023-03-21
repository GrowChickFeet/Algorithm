package 성민재.week4;
import java.io.*;
import java.util.StringTokenizer;

public class BJ2873_빙산 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] drow = {0, 1, 0, -1}; //우 하 좌 상
    static int[] dcol = {1, 0, -1, 0}; //우 하 좌 상
    static boolean[][] visited_m, visited_d;
    static int[][] area;
    static int n,m;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        area = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        flag = true;
        int ans = 0;
        int t = 3;
        while(flag) {
            visited_m = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (area[i][j] != 0) {
                        melt(new Node(i, j));
                        visited_m[i][j] = true;
                    }
                }
            }

            ans++;
            int check = 0;
            visited_d = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (area[i][j] != 0 && !visited_d[i][j]) {
                        dfs(new Node(i, j));
                        check++;
                    }
                }
            }
            if(check >= 2) flag = false;
            if(check == 0) {
                ans = 0;
                flag = false;
            }
        }
        System.out.println(ans);
    }
    private static void melt(Node node) {
        int zero_cnt = 0;
        for (int k = 0; k < 4; k++) {
            Node nx = new Node(node.row + drow[k], node.col + dcol[k]);
            if(!isIn(nx)) continue;
            if(visited_m[nx.row][nx.col]) continue;
            if(area[nx.row][nx.col] == 0)
                zero_cnt++;
        }
        area[node.row][node.col] -= zero_cnt;
        if(area[node.row][node.col] < 0) {
            area[node.row][node.col] = 0;
        }
    }
    private static void dfs(Node node) {
        visited_d[node.row][node.col] = true;

        for (int k = 0; k < 4; k++) {
            Node nx = new Node(node.row + drow[k], node.col + dcol[k]);
            if(!isIn(nx)) continue;
            if(visited_d[nx.row][nx.col]) continue;
            if(area[nx.row][nx.col] > 0)
                dfs(nx);
        }
    }
    private static boolean isIn(Node nx){
        return nx.row >= 0 && nx.row < n && nx.col >= 0 && nx.col < m;
    }

    static class Node{
        int row;
        int col;
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

