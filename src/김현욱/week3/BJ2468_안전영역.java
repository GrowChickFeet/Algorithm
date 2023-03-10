package 김현욱.week3;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2468_안전영역 {
    public static boolean isIn(int n,int x,int y){
        return 0<=x&&x<n&&0<=y&&y<n;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] mv = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int n = Integer.parseInt(br.readLine());
        int[][] maze = new int[n][n];
        int minHeight= Integer.MAX_VALUE;
        int maxHeight = 0;
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                maze[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight,maze[i][j]);
                maxHeight = Math.max(maxHeight,maze[i][j]);
            }
        }
        int result = 1;
        for(int h=minHeight;h<maxHeight;h++) {
            int safetyZone = 0;
            boolean[][] visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && maze[i][j] >h) {//방문하지 않았고, 잠기지않는 지역이라면
                        Queue<Integer> q = new LinkedList<>();
                        q.offer(i*n+j);
                        visited[i][j] = true;

                        while(!q.isEmpty()){
                            int node = q.poll();
                            int x = node/n;
                            int y = node%n;

                            for(int d=0;d<mv.length;d++){
                                int nx = x+mv[d][0];
                                int ny = y+mv[d][1];

                                if(isIn(n,nx,ny) && !visited[nx][ny] && maze[nx][ny] > h){
                                    visited[nx][ny] = true;
                                    q.offer(nx*n+ny);
                                }
                            }
                        }

                        safetyZone++;
                    }
                }
            }
            result = Math.max(result,safetyZone);
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
