package 김현욱.additional;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2567_색종이 {
    public static boolean isIn(int x,int y) {//범위안에 있는지에 대한 판별함수
        return 0<=x&&x<=100&&0<=y&&y<=100;//범위안에 있으면 true, 아니면 false
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//입력을 받기위한 버퍼리더
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//출력을 위한 버퍼라이터

        int n = Integer.parseInt(br.readLine());;//스카프의 수

        boolean[][] maze = new boolean[101][101];//스카프를 놓을 천
        boolean[][] check = new boolean[101][101];//bfs를 위한 visited배열
        int[][] mv = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};//상하좌우로 움직일 배열


        for(int i=0;i<n;i++) {//n개의 스카프를 입력받을 반복문
            StringTokenizer st = new StringTokenizer(br.readLine());//x좌표와 y좌표를 받을 토크나이저
            int x = Integer.parseInt(st.nextToken());//좌측하단 x좌표
            int y = Integer.parseInt(st.nextToken());//좌측하단 y좌표

            for(int row=0;row<10;row++) {//10개만큼의 행을 반복할 반복문
                for(int col=0;col<10;col++) {//10개만큼의 열을 반복할 반복문
                    maze[x+row][y+col]=true;//10x10을 true로 만들어줌
                }
            }
        }


        int answer = 0;//둘레 길이를 저장할 변수
        for(int i=0;i<=100;i++) {//모든 행을 조사하기위한 for문
            for(int j=0;j<=100;j++) {//모든 열을 조사하기위한 for문
                if(maze[i][j] && !check[i][j]) {//만약 i,j가 스카프면서 탐색하지 않았던곳이라면
                    Queue<int[]> q = new LinkedList<>();//bfs를 위한 큐 준비
                    q.offer(new int[] {i,j});//현재 좌표 저장
                    check[i][j] = true;//현재 좌표를 방문했다고 체크

                    while(!q.isEmpty()) {//큐가 빌때까지 실행
                        int[] node = q.poll();//현재 노드 가져오기
                        int x = node[0];//현재x좌표
                        int y = node[1];//현재 y좌표

                        for(int d=0;d<mv.length;d++) {//상하좌우를 돌며
                            int nx = x+mv[d][0];//다음x좌표
                            int ny = y+mv[d][1];//다음y좌표

                            if(!isIn(nx,ny) || !maze[nx][ny]) answer++;//만약 다음 좌표가 범위밖이거나 스카프가 아니면, 해당 변의 길이(1)을 더해줌

                            if(isIn(nx,ny) && maze[nx][ny] && !check[nx][ny]) {//다음좌표가 범위 안이면서, 방문하지 않은 스카프라면
                                q.offer(new int[] {nx,ny});//큐에 다음좌표를 넣어주고
                                check[nx][ny]=true;//다음좌표를 방문체크
                            }
                        }
                    }
                }
            }
        }


        bw.write(Integer.toString(answer));//답을 출력
        br.close();//자원반환
        bw.close();//자원반환
    }
}
