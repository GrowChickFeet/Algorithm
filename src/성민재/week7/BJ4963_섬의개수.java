package 성민재.week7;


import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4963_섬의개수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();
    static int w, h;
    static int[][] map, dir = {{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {-1,-1},{-1,1},{1,-1}}; //우하 좌상 대각
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {

        while(true){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0)
                break;

            map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int ans = 0;
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        bfs(i,j);
                        ans++;
                    }
                }
            }
            sb.append(ans).append("\n");

        }
        bw.write(sb.toString());
        bw.close();
    }

    private static void bfs(int i, int j) {
        Queue<Node> q = new ArrayDeque<>();
        visited[i][j] = true;
        q.add(new Node(i,j));

        while(!q.isEmpty()){
            Node cur = q.poll();;

            for (int k = 0; k < dir.length; k++) {
                Node nx = new Node(cur.row + dir[k][0], cur.col + dir[k][1]);

                if(!isIn(nx)) continue;
                if(visited[nx.row][nx.col]) continue;
                 if(map[nx.row][nx.col] == 1){
                    q.add(nx);
                    visited[nx.row][nx.col] = true;
                }
            }
        }
    }
    private static boolean isIn(Node nx) {
        return nx.row >= 0 && nx.row < h && nx.col >= 0 && nx.col < w;
    }
    static class Node{
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}