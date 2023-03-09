package 성민재.week3;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2468_안전영역 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, matrix[][], ans;
    static boolean[][] visited;
    static int[] d_row = {0, 1, 0, -1}; //우 하 좌 상
    static int[] d_col = {1, 0, -1, 0}; //우 하 좌 상

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        matrix = new int[N][N];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(matrix[i][j], max);
            }
        }

        ans = Integer.MIN_VALUE;
        for (int i = 0; i <= max; i++) { //노트에서 아무지역도 안잠기는 경우가 있다고 함
            visited = new boolean[N][N];
            ans = Math.max(bfs(i),ans); //안전거리가 최대인 지역 찾기
        }

        System.out.println(ans);
    }
    private static int bfs(int height) {
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(matrix[i][j] <= height || visited[i][j]) continue; //이미 잠겨 있거나 방문했다면 continue;

                Queue<Node> q = new ArrayDeque<>(); //큐생성
                q.add(new Node(i,j)); //큐에 시작지점 넣기
                visited[i][j] = true; //시작 지점 방문처리

                while(!q.isEmpty()){
                    Node cur = q.poll();

                    for (int k = 0; k < 4; k++) {
                        Node nx = new Node(cur.row + d_row[k], cur.col + d_col[k]); //상하좌우 탐색

                        if (!isIn(nx)) continue; //범위를 벗어난다면 continue
                        if (!visited[nx.row][nx.col] && matrix[nx.row][nx.col] > height) { // 아직 방문하지 않았고 잠기지 않았다면 q에 삽입
                            q.add(nx);
                            visited[nx.row][nx.col] = true; //방문 처리.
                        }
                    }
                }
                num++; // 안전구역을 확인했으니 하나 증가
            }
        }
        return num;
    }
    private static boolean isIn(Node n){
        return n.row >= 0 && n.row < N && n.col >= 0 && n.col < N;
    } //범위 확인

    static class Node{
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

