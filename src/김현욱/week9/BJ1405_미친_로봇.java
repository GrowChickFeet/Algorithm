package 김현욱.week9;

import java.io.*;
import java.util.StringTokenizer;

public class BJ1405_미친_로봇 {
    static int n;
    static int dir[] = new int[4];
    static int[][] mv = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};//동서남북

    static double result = 0;

    static boolean[][] visited = new boolean[100][100];

    public static boolean isIn(int x,int y){
        return 0<=x && x<100 && 0<=y&&y<100;
    }

    public static void dfs(int index,int x,int y,double percent){
        if(index == n){
            result+=percent;
            return;
        }

        for(int d=0;d<dir.length;d++){
            int nx = x+mv[d][0];
            int ny = y+mv[d][1];
            if(dir[d]!=0 && isIn(nx,ny) && !visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(index+1,nx,ny, percent * (dir[d]*0.01));
                visited[nx][ny] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());


        int dirCount = 0;//갈 수 있는 방향의 개수
        for(int i=0;i<4;i++){
            dir[i] = Integer.parseInt(st.nextToken());
            if(dir[i]!=0) dirCount++;
        }

        visited[50][50] = true;//시작을 중앙으로 잡아둠
        dfs(0,50,50,1);


        bw.write(Double.toString(result));
        br.close();
        bw.close();
    }
}
