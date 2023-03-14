package 김현욱.week4;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2573_빙산 {
    static int n, m;
    static int[][] maze;
    static int[][] mv = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    static boolean isIn(int x,int y){
        return 0<=x&&x<n&&0<=y&&y<m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            maze[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int result = 0;
        for (int year = 1; ; year++) {
            meltDown();
            int icebergsCount = getIcebergsCount();
            if(icebergsCount >= 2){
                result = year;
                break;
            }
            else if(icebergsCount == 0){
                break;
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }

    private static int getIcebergsCount() {
        boolean[][] visited = new boolean[n][m];
        int result = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && maze[i][j]!=0){//방문하지않은 빙산이라면
                    result++;

                    Queue<Integer> q = new LinkedList<>();
                    q.offer(i*m+j);
                    visited[i][j] = true;

                    while(!q.isEmpty()){
                        int index = q.poll();
                        int x = index/m;
                        int y = index%m;

                        for(int d=0;d<mv.length;d++){
                            int nx = x+mv[d][0];
                            int ny = y+mv[d][1];

                            if(isIn(nx,ny) && maze[nx][ny]!=0 && !visited[nx][ny]){//다음 빙산이면
                                visited[nx][ny] = true;//체크
                                q.offer(nx*m+ny);//큐에 넣기
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private static void meltDown() {
        int[][] meltCount = new int[n][m];// meltCount[i][j] = i,j에 있는 빙산이 얼마나 녹을지 저장하는 배열
        for(int x=0;x<n;x++){
            for(int y=0;y<m;y++){
                if(maze[x][y] != 0){//빙산이라면
                    for(int d=0;d<mv.length;d++){//상하좌우를 돌며
                        int nx = x+mv[d][0];
                        int ny = y+mv[d][1];

                        if(isIn(nx,ny) && maze[nx][ny] == 0){//범위안에 존재하고 호수라면
                            meltCount[x][y]++;//녹는 수치 1 증가
                        }
                    }
                }
            }
        }

        for(int x=0;x<n;x++){
            for(int y=0;y<m;y++){
                maze[x][y] = Math.max(0,maze[x][y]-meltCount[x][y]);//0보다 작아질 수 없으므로 뺀 수와 0중 큰수로 셋팅
            }
        }
    }
}
