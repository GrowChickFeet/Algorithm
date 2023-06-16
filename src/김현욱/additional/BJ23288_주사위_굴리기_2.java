package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ23288_주사위_굴리기_2 {
    static int n,m,k;
    static int[][] maze;
    static int[][] mv = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};//북->동->남->서
    static int[][] count;//해당 포인트가 몇점인지 미리 저장

    static class Dice{
        int up,down,left,right,front,back,d;
        int x,y;

        public Dice() {
            this.up = 1;
            this.down = 6;
            this.left = 4;
            this.right = 3;
            this.front = 5;
            this.back = 2;
            this.d = 1;
            this.x=0;
            this.y=0;
        }

        public void move(){
            if(d == 0){//북쪽으로 이동
                //좌, 우는 그대로
                //앞->위, 위->뒤, 뒤->아래, 아래->앞
                int temp = front;
                front = down;
                down = back;
                back = up;
                up = temp;
            }
            else if(d == 1){//동쪽으로 이동
                //앞, 뒤는 그대로
                //아래 -> 왼쪽, 왼쪽->위, 위->오른쪽, 오른쪽->아래
                int temp = down;
                down = right;
                right = up;
                up = left;
                left = temp;
            }
            else if(d==2){//남쪽으로 이동
                //좌, 우는 그대로
                //앞-> 아래, 아래->뒤, 뒤->위, 위->앞
                int temp = front;
                front = up;
                up = back;
                back = down;
                down = temp;
            }
            else{//서쪽으로 이동
                //앞, 뒤는 그대로
                //아래 ->오른쪽, 오른쪽 ->위, 위->왼쪽, 왼쪽->아래
                int temp = down;
                down = left;
                left = up;
                up = right;
                right = temp;
            }

            x = x+mv[d][0];
            y = y+mv[d][1];
        }
    }

    static boolean isIn(int x,int y){
        return 0<=x&&x<n&&0<=y&&y<m;
    }

    static void initCount(){
        boolean[][] visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j]){
                    int c = 1;//자신을 포함한 개수
                    int value = maze[i][j];//현재 좌표에 존재하는 값
                    ArrayList<int[]> points = new ArrayList<>();//경로들을 저장할 리스트
                    points.add(new int[]{i,j});//해당 좌표 add

                    Queue<int[]> q = new LinkedList<>();//bfs를 위한 큐
                    q.offer(new int[]{i,j});//현재 좌표 add
                    visited[i][j] = true;//현재좌표 방문표시

                    while(!q.isEmpty()){
                        int[] now = q.poll();
                        int x = now[0];
                        int y = now[1];

                        for(int d=0;d<mv.length;d++){
                            int nx = x+mv[d][0];
                            int ny = y+mv[d][1];

                            if(isIn(nx,ny) && !visited[nx][ny] && maze[nx][ny] == value){
                                int[] next = new int[]{nx,ny};
                                visited[nx][ny] = true;
                                points.add(next);
                                q.offer(next);
                                c++;
                            }
                        }
                    }

                    //해당 경로에 모두 값을 갱신
                    for(int[] point : points){
                        int x = point[0];
                        int y = point[1];

                        count[x][y] = c;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        count = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        initCount();

        Dice dice = new Dice();
        int result = 0;
        for(int i=0;i<k;i++){
            //주사위가 이동할 위치가 갈 수 있는지 체크, 갈 수 없으면 반대방향으로 셋팅
            if(!isIn(dice.x+mv[dice.d][0],dice.y+mv[dice.d][1])){
                dice.d = (dice.d+2)%mv.length;
            }

            //주사위 이동
            dice.move();

            //점수 획득
            result += count[dice.x][dice.y] * maze[dice.x][dice.y];

            //이동방향 설정
            int down = dice.down;
            int value = maze[dice.x][dice.y];

            if(down > value){
                dice.d = (dice.d+1) % mv.length;
            }
            else if(down < value){
                dice.d = (dice.d - 1 + mv.length) % mv.length;
            }
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
