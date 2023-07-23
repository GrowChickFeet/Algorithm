package 김현욱.week13;


import java.io.*;
import java.util.Arrays;

public class BJ1347_미로만들기 {
    static int[][] mv = new int[][]{{1,0},{0,-1},{-1,0},{0,1}};//남, 서, 북, 동

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

        char[][] maze = new char[200][200];
        for(int i=0;i<200;i++){
            Arrays.fill(maze[i],'#');
        }

        int x = 100;//기본x좌표
        int y = 100;//기본y좌표
        int d = 0;//현재 방향은 남쪽(0)

        int max_x = x;//최대 x좌표
        int min_x = x;//최소 x좌표
        int max_y = y;//최대 y좌표
        int min_y = y;//최소 y좌표

        maze[x][y] = '.';

        for(char c : arr){
            switch(c){
                case 'R':
                    d = (d+1)%mv.length;
                    break;
                case 'L':
                    d = (d+mv.length - 1)%mv.length;
                    break;
                case 'F':
                    x = x+mv[d][0];
                    y = y+mv[d][1];

                    max_x = Math.max(max_x,x);
                    min_x = Math.min(min_x,x);
                    max_y = Math.max(max_y,y);
                    min_y = Math.min(min_y,y);
                    maze[x][y] = '.';
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=min_x;i<=max_x;i++){
            for(int j=min_y;j<=max_y;j++){
                sb.append(maze[i][j]);
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
