package 김현욱.week10;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ19236_청소년_상어 {
    static int ret = 0;
    static int[][] mv = new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    static class Fish implements Comparable<Fish>{
        int x, y, d, num;
        boolean isShark;

        public Fish(int x, int y, int d, int num, boolean isShark) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.num = num;
            this.isShark = isShark;
        }

        public Fish(Fish f) {
            this.x = f.x;
            this.y = f.y;
            this.d = f.d;
            this.num = f.num;
            this.isShark = f.isShark;
        }

        @Override
        public int compareTo(Fish o) {
            return Integer.compare(this.num,o.num);
        }

        @Override
        public String toString() {
            return "{" +
                    "num=" + num +
                    ", d=" + d +
                    '}';
        }
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }

    static Fish[][] copyMaze(Fish[][] maze) {
        Fish[][] copy = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Fish f = maze[i][j];
                if(f!=null) copy[i][j] = new Fish(f);
            }
        }

        return copy;
    }

    static void printMaze(Fish[][] maze,int x,int y){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(maze[i][j] == null){
                    System.out.print(i == x && j == y ? "shark\t\t\t" :"null\t\t\t");
                }
                else {
                    System.out.print(maze[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("=========================================");
    }

    static void moveFish(Fish[][] maze,int sx,int sy){
        List<Fish> list = new ArrayList<>();//순서대로 저장할 배열

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(maze[i][j]!=null){
                    list.add(maze[i][j]);
                }
            }
        }

        Collections.sort(list);//물고기를 번호순으로 정렬

        for(int i=0;i<list.size();i++){
            Fish f = list.get(i);
            int x = f.x;
            int y = f.y;
            int d = f.d;

            boolean canGo = false;
            for(int j=0;j<8;j++){
                int nx = x+mv[d][0];
                int ny = y+mv[d][1];

                if(isIn(nx,ny) && ((maze[nx][ny]!=null) || (!(nx==sx && ny==sy) && maze[nx][ny] == null))){
                    canGo = true;
                    break;
                }

                d = (d+1)%mv.length;
            }

            if(canGo){
                int nx = x+mv[d][0];
                int ny = y+mv[d][1];
                if(maze[nx][ny] == null){//빈칸이면
                    f.x = nx;
                    f.y = ny;
                    f.d = d;

                    maze[nx][ny] = f;
                    maze[x][y] = null;
                }
                else {//물고기있으면
                    Fish target = maze[nx][ny];

                    target.x = x;
                    target.y = y;

                    f.x = nx;
                    f.y = ny;
                    f.d = d;

                    maze[nx][ny] = f;
                    maze[x][y] = target;
                }
            }
        }
    }

    static void dfs(int x, int y, int d, int score, Fish[][] maze) {
        //값 갱신
        ret = Math.max(ret,score);
        //dfs를 위해 수행 하기전 복사
        Fish[][] copy = copyMaze(maze);




        //물고기 이동
        moveFish(copy,x,y);

        //상어 이동할 수 있는 만큼 ㄱㄱ
        for(int i=0;i<3;i++){//최대 3칸
            int nx = x+mv[d][0]*(i+1);
            int ny = y+mv[d][1]*(i+1);

            if(!isIn(nx,ny)) continue;
            if(copy[nx][ny] == null) continue;

            Fish target = copy[nx][ny];
            int targetD = target.d;

            copy[nx][ny] = null;

            dfs(nx,ny,targetD,score+target.num,copy);

            copy[nx][ny] = target;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Fish[][] maze = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;

                maze[i][j] = new Fish(i, j, d, num, false);
            }
        }

        int score = maze[0][0].num;
        int d = maze[0][0].d;
        maze[0][0] = null;

        dfs(0, 0, d, score, maze);

        bw.write(Integer.toString(ret));
        br.close();
        bw.close();
    }
}
