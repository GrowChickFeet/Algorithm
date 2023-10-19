package 김현욱.week20;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1388_바닥_장식 {
    static StringTokenizer st;
    static int n,m;
    static int[][][] mv = new int[][][]{
            {
                    {0,1},{0,-1}
            },
            {
                    {1,0},{-1,0}
            }
    };

    static boolean isIn(int x,int y){
        return 0<=x&&x<n&&0<=y&&y<m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] maze = new int[n][m];
        for(int i=0;i<n;i++){
            char[] line = br.readLine().toCharArray();

            for(int j=0;j<m;j++){
                maze[i][j] = (line[j] == '-' ? 0 : 1);
            }
        }

        boolean[][] visited = new boolean[n][m];
        int result = 0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j]){
                    visited[i][j] = true;
                    Queue<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{i,j,maze[i][j]});

                    while(!q.isEmpty()){
                        int[] arr = q.poll();
                        int x = arr[0];
                        int y = arr[1];
                        int type = arr[2];

                        for(int d=0;d<mv[type].length;d++){
                            int nx = x+mv[type][d][0];
                            int ny = y+mv[type][d][1];

                            if(isIn(nx,ny) && !visited[nx][ny] && maze[nx][ny] == type){
                                q.offer(new int[]{nx,ny,type});
                                visited[nx][ny] = true;
                            }
                        }
                    }
                    result++;
                }
            }
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
