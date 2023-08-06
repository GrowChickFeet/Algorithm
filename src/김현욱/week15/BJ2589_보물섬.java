package 김현욱.week15;

import java.io.*;
import java.util.*;

public class BJ2589_보물섬 {
    static int[][] mv = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    static int n,m;
    static boolean isIn(int x,int y){
        return 0<=x&&x<n&&0<=y&&y<m;
    }
    static class Node{
        int x,y,dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        char[][] maze = new char[n][m];
        int result = 0;
        for(int i=0;i<n;i++){
            maze[i] = br.readLine().toCharArray();
        }

        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++) {
                if(maze[i][j] =='L'){
                    boolean[][] visited = new boolean[n][m];
                    Queue<Node> q = new LinkedList<>();
                    visited[i][j] = true;
                    q.offer(new Node(i,j,0));
                    List<Integer> edgeDist = new ArrayList<>();
                    while(!q.isEmpty()){
                        Node node = q.poll();
                        int x = node.x;
                        int y = node.y;
                        int dist = node.dist;

                        result = Math.max(result,dist);

                        for(int d=0;d<mv.length;d++){
                            int nx = x+mv[d][0];
                            int ny = y+mv[d][1];

                            if(isIn(nx,ny) && !visited[nx][ny] && maze[nx][ny] == 'L'){
                                visited[nx][ny] = true;
                                q.offer(new Node(nx,ny,dist+1));
                            }
                        }
                    }
                }
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
