package 김현욱.week13;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1743_음식물피하기 {
    static int[][] mv = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    static int n,m,k;
    static boolean isIn(int x,int y){
        return 0<=x && x<n && 0<=y && y<m;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        boolean[][] isTrash = new boolean[n][m];
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;

            isTrash[x][y] = true;
        }

        boolean[][] visited = new boolean[n][m];
        int result = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && isTrash[i][j]){
                    Queue<Integer> q = new LinkedList<>();
                    int size = 1;
                    q.offer(i*m+j);
                    visited[i][j] = true;

                    while(!q.isEmpty()){
                        int idx = q.poll();
                        int x = idx/m;
                        int y = idx%m;

                        for(int d=0;d<mv.length;d++){
                            int nx = x+mv[d][0];
                            int ny = y+mv[d][1];

                            if(isIn(nx,ny) && !visited[nx][ny] && isTrash[nx][ny]){
                                visited[nx][ny] = true;
                                q.offer(nx*m+ny);
                                size++;
                            }
                        }
                    }
                    result = Math.max(result,size);
                }
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
