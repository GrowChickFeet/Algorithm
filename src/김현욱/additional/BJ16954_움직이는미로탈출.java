package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ16954_움직이는미로탈출 {
    static char[] periodLine = new char[]{'.','.','.','.','.','.','.','.'};
    static int[][] mv = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{-1,1},{-1,-1},{1,-1},{1,1},{0,0}};
    static final int MAX_HEIGHT = 8;
    static final int MAX_WIDTH = 8;
    static final int MAX_TURN = 1000;
    static class Point{
        int x,y,turn;
        public Point(int x,int y,int turn){
            this.x=x;
            this.y=y;
            this.turn=turn;
        }
    }

    public static boolean isIn(int x,int y){
        return 0<=x&&x<MAX_HEIGHT&&0<=y&&y<MAX_WIDTH;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<char[]> maze = new ArrayList<>();

        for(int i=0;i<MAX_HEIGHT;i++){
            maze.add(br.readLine().toCharArray());
        }
        Collections.reverse(maze);//계산하기 편하게 거꾸로 뒤집음
        for(int i=0;i<MAX_TURN;i++){
            maze.add(periodLine);
        }

        Point ukJae = new Point(0,0,0);

        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[MAX_HEIGHT][MAX_WIDTH][MAX_TURN];
        q.offer(ukJae);
        visited[0][0][0] = true;

        int result = 0;

        while(!q.isEmpty()){
            Point now = q.poll();
            int x = now.x;//상대 x좌표
            int y = now.y;//상대 y좌표
            int turn = now.turn;//현재 턴
            if(x == MAX_HEIGHT-1 && y == MAX_WIDTH-1){
                result = 1;
                break;
            }

            for(int d=0;d<mv.length;d++){
                int nx = x+mv[d][0];
                int ny = y+mv[d][1];
                int targetX = nx+turn;
                /*
                * 가려고 하는칸이 범위 안에 있어야함.
                * 가려고 하는칸이 . 이여야함
                * 가려고하는 칸의 윗칸이 .이여야 함 ( 어차피 하나 내려오기때문에 사라짐 )
                * */
                if(isIn(nx,ny) &&!visited[nx][ny][turn+1]&& maze.get(targetX)[ny] == '.' && maze.get(targetX+1)[ny] == '.'){
                    q.offer(new Point(nx,ny,turn+1));
                    visited[nx][ny][turn+1]= true;
                }
            }
        }

        bw.write(Integer.toString(result));
        bw.close();
        br.close();
    }
}
