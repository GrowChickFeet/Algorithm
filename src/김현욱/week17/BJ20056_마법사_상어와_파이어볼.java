package 김현욱.week17;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class BJ20056_마법사_상어와_파이어볼 {
    static int[][] mv = new int[][] {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    static class FireBall{
        int r,c,m,s,d;
        public FireBall(int r,int c,int m, int s, int d) {
            this.r=r;
            this.c=c;
            this.m=m;
            this.s = s;
            this.d = d;
        }

        public void move(int N) {
            int nr = (r+mv[d][0]*(s%N)+N)%N;
            int nc = (c+mv[d][1]*(s%N)+N)%N;


            this.r=nr;
            this.c=nc;
        }

        @Override
        public String toString() {
            return "r="+r+", c="+c+", m="+m+", s="+s+", d="+d;
        }
    }

    static Queue<FireBall>[][] fireBallMove(Queue<FireBall>[][] maze){
        int N = maze.length;
        Queue<FireBall>[][] result = new LinkedList[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                result[i][j]=new LinkedList<>();
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                while(!maze[i][j].isEmpty()) {
                    FireBall fireBall = maze[i][j].poll();

                    fireBall.move(N);
                    result[fireBall.r][fireBall.c].offer(fireBall);
                }
            }
        }

        return result;
    }

    static void fireBallAction(Queue<FireBall>[][] maze) {
        int N = maze.length;

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(maze[i][j].size()>=2) {
                    int weight = 0;
                    int speed = 0;
                    boolean allEven = true;//모두 짝
                    boolean allOdd = true;//모두 홀

                    int size = maze[i][j].size();

                    while(!maze[i][j].isEmpty()) {
                        FireBall fb = maze[i][j].poll();
                        weight+=fb.m;
                        speed+=fb.s;
                        if(fb.d%2!=0) allEven = false;
                        if(fb.d%2!=1) allOdd = false;
                    }

                    int w = weight/5;
                    if(w ==0) continue;
                    int index = (allEven || allOdd ? 0 : 1);
                    for(;index<8;index+=2) {
                        maze[i][j].offer(new FireBall(i,j,w,speed/size,index));
                    }
                }
            }
        }
    }

    public static Object calcWeight(Queue<FireBall>[][] maze) {
        // TODO Auto-generated method stub
        int result = 0;
        int N = maze.length;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                while(!maze[i][j].isEmpty()) {
                    result+=maze[i][j].poll().m;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        int K = Integer.valueOf(st.nextToken());

        Queue<FireBall>[][] maze = new LinkedList[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                maze[i][j]=new LinkedList<>();
            }
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.valueOf(st.nextToken())-1;
            int c = Integer.valueOf(st.nextToken())-1;
            int m = Integer.valueOf(st.nextToken());
            int s = Integer.valueOf(st.nextToken());
            int d = Integer.valueOf(st.nextToken());

            maze[r][c].offer(new FireBall(r,c,m,s,d));
        }

        for(int i=0;i<K;i++) {
            maze = fireBallMove(maze);
            fireBallAction(maze);
        }

        sb.append(calcWeight(maze));

        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}
