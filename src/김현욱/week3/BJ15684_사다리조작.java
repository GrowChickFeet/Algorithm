package 김현욱.week3;

import java.io.*;
import java.util.StringTokenizer;

public class BJ15684_사다리조작 {
    public static int[][] edge;
    public static int result = Integer.MAX_VALUE;
    public static boolean[][] maze;
    public static int n,m,h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        maze = new boolean[h+1][n+2];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            maze[a][b] = true;
        }

        dfs(0);
        bw.write(Integer.toString(result == Integer.MAX_VALUE ? -1 : result));
        br.close();
        bw.close();
    }

    private static void dfs(int count) {
        if(check()){
            result = Math.min(result,count);
            return;
        }
        if(count == 3) return;

        for(int i=1;i<=h;i++){
            for(int j=1;j<=n-1;j++){
                if(!maze[i][j]){
                    maze[i][j] = true;
                    dfs(count+1);
                    maze[i][j] = false;
                }
            }
        }
    }

    private static boolean check() {
        for(int i=1;i<=n;i++){
            int col = i;
            for(int j=1;j<=h;j++){
                if(maze[j][col-1]) col--;
                else if(maze[j][col]) col++;
            }
            if(col != i) return false;
        }
        return true;
    }
}
