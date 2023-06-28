package 김현욱.additional;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17836_공주님을구해라 {
    static int N,M,T;
    static int maze[][];
    static int[][] mv = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    static class Node{
        int x,y,time;
        boolean isGram;

        public Node(int x, int y, int time,boolean isGram) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.isGram = isGram;
        }
    }

    public static boolean isIn(int x,int y){
        return 0<=x&&x<N&&0<=y&&y<M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        maze = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Node> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];//visited[i][j][0] = 그람을 들지 않고 i,j에 도착한적 있는지, visited[i][j][1] = 그람을 들고 i,j에 도착한적 있는지
        q.offer(new Node(0,0,0,false));
        visited[0][0][0] = true;

        int result = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Node node = q.poll();

            int x = node.x;
            int y = node.y;
            int time = node.time;
            boolean isGram = node.isGram;

            if(x == N-1 && y == M-1 && time <= T){
                result = Math.min(result,time);
                continue;
            }

            for(int d=0;d<mv.length;d++){
                int nx = x+mv[d][0];
                int ny = y+mv[d][1];

                if(!isIn(nx,ny)) continue;

                if(isGram && !visited[nx][ny][1]){
                    q.offer(new Node(nx,ny,time+1,true));
                    visited[nx][ny][1]=true;
                }
                else if(!isGram && maze[nx][ny] != 1 && !visited[nx][ny][0]){
                    q.offer(new Node(nx,ny,time+1,maze[nx][ny] == 2));
                    visited[nx][ny][maze[nx][ny] == 2 ? 1 : 0] = true;
                }
            }
        }

        bw.write(result != Integer.MAX_VALUE ? Integer.toString(result) : "Fail");
        br.close();
        bw.close();
    }
}
