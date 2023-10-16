package 김현욱.week19;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13565_침투 {
    static int[][] mv = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    static boolean isIn(int n,int m ,int x,int y){
        return 0<=x&&x<n&&0<=y&&y<m;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] maze = new int[n+2][m];

        for(int i=1;i<=n;i++){
            char[] line = br.readLine().toCharArray();
            for(int j=0;j<m;j++){
                maze[i][j] = line[j]-'0';
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n+2][m];

        boolean result = false;
        q.offer(new int[]{0,0});
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];

            if(x == n+1){
                result = true;
                break;
            }

            for(int d=0;d<mv.length;d++){
                int nx = x+mv[d][0];
                int ny = y+mv[d][1];

                if(isIn(n+2,m,nx,ny) && !visited[nx][ny] && maze[nx][ny] == 0){
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                }
            }
        }
        bw.write(result ? "YES" : "NO");
        br.close();
        bw.close();
    }
}
