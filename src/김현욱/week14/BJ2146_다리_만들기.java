package 김현욱.week14;

import java.io.*;
import java.util.*;

public class BJ2146_다리_만들기 {
    static StringTokenizer st;
    static int[][] mv = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int n;

    static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static class Land {
        int x, y, lNum;
        int cost;

        public Land(int x, int y, int lNum) {
            this.x = x;
            this.y = y;
            this.lNum = lNum;
        }

        public Land(int x, int y, int lNum, int cost) {
            this.x = x;
            this.y = y;
            this.lNum = lNum;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        int[][] maze = new int[n][n];
        for (int i = 0; i < n; i++) {
            maze[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();
        }


        int[][] visited = new int[n][n];//방문 체크를 위한
        int landNum = 1;//몇번째 섬인지 체크할 변수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (maze[i][j] == 1 && visited[i][j] == 0) {//방문하지 않았으며 땅이면 섬으로 묶음
                    visited[i][j] = landNum;
                    Queue<Land> q = new LinkedList<>();
                    q.offer(new Land(i, j, landNum++));
                    //bfs진행
                    while (!q.isEmpty()) {
                        Land land = q.poll();
                        int x = land.x;
                        int y = land.y;
                        int lNum = land.lNum;

                        for (int d = 0; d < mv.length; d++) {
                            int nx = x+mv[d][0];
                            int ny = y+mv[d][1];

                            if(isIn(nx,ny) && maze[nx][ny] == 1 && visited[nx][ny] == 0){
                                visited[nx][ny] = lNum;
                                q.offer(new Land(nx,ny,lNum));
                            }
                        }
                    }
                }
            }
        }
        int result = Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j] != 0){
                    Queue<Land> q = new LinkedList<>();
                    q.offer(new Land(i,j,visited[i][j],0));
                    int[][] dist = new int[n][n];
                    for(int k=0;k<n;k++){
                        Arrays.fill(dist[k],Integer.MAX_VALUE);
                    }
                    dist[i][j] = 0;

                    while(!q.isEmpty()){
                        Land land = q.poll();
                        int x = land.x;
                        int y = land.y;
                        int cost = land.cost;
                        int lNum = land.lNum;
                        if(cost!=dist[x][y]) continue;

                        for(int d=0;d<mv.length;d++){
                            int nx = x+mv[d][0];
                            int ny = y+mv[d][1];

                            if(!isIn(nx,ny)) continue;//범위 밖이면 continue
                            if(visited[nx][ny] == lNum) continue;//자신의 땅이면 continue
                            if(visited[nx][ny] != 0){//다른 땅을 만났을 때 갱신
                                result = Math.min(result,cost);
                                continue;
                            }
                            if(dist[nx][ny] > cost + 1){//더 짧은 다리라면 큐에 넣기
                                dist[nx][ny] = cost+1;
                                q.offer(new Land(nx,ny,lNum,cost+1));
                            }
                        }
                    }
                }
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
