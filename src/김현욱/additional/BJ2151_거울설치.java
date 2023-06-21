package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ2151_거울설치 {
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Node{
        Point point;
        int doorCount;
        int d;
        public Node(int x,int y,int doorCount,int d){
            this.point = new Point(x,y);
            this.doorCount = doorCount;
            this.d=d;
        }

        public Node(Point point, int doorCount,int d) {
            this.point = point;
            this.doorCount = doorCount;
            this.d=d;
        }
    }
    static int[][] mv = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    static boolean isIn(int n,int x,int y){
        return 0<=x&&x<n&&0<=y&&y<n;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Point> doors = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        int[][][] visited = new int[n][n][4];//방문체크배열
        char[][] maze = new char[n][n];
        for(int i=0;i<n;i++){
            maze[i] = br.readLine().toCharArray();
            for(int j=0;j<n;j++){
                Arrays.fill(visited[i][j],Integer.MAX_VALUE);
                if(maze[i][j] == '#'){
                    doors.add(new Point(i,j));
                }
            }
        }

        Queue<Node> q = new LinkedList<>();
        Point door = doors.get(0);
        for(int d=0;d<mv.length;d++) {
            visited[door.x][door.y][d] = 0;
            int nx = door.x+mv[d][0];
            int ny = door.y+mv[d][1];
            if(!isIn(n,nx,ny)) continue;
            if(maze[nx][ny]!='*') {
                q.offer(new Node(nx,ny, 0, d));
                visited[nx][ny][d] = 0;
            }
            if(maze[nx][ny] =='!'){ // 거울이면 진행방향의 좌,우로 꺾임
                int nd = (d+1)%mv.length;

                if(visited[nx][ny][nd] > visited[door.x][door.y][d]+1){
                    visited[nx][ny][nd] = 1;
                    q.offer(new Node(nx,ny,1,nd));
                }

                nd = (d+mv.length-1)%mv.length;
                if(visited[nx][ny][nd] > visited[door.x][door.y][d]+1){
                    visited[nx][ny][nd] = 1;
                    q.offer(new Node(nx,ny,1,nd));
                }
            }
        }


        Point target = doors.get(1);

        int result = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Node node = q.poll();
            int x = node.point.x;
            int y = node.point.y;
            int doorCount = node.doorCount;
            int d = node.d;

            if(visited[x][y][d] != doorCount) continue;
            if(x == door.x && y == door.y) continue;

            if(x == target.x && y == target.y){
                result = Math.min(result,doorCount);
                continue;
            }

            int nx = x+mv[d][0];
            int ny = y+mv[d][1];

            if(isIn(n,nx,ny) && maze[nx][ny] != '*'){
                if(visited[nx][ny][d] > visited[x][y][d]){
                    visited[nx][ny][d] = visited[x][y][d];
                    q.offer(new Node(nx,ny,doorCount,d));
                }
                if(maze[nx][ny] =='!'){ // 거울이면 진행방향의 좌,우로 꺾임
                    int nd = (d+1)%mv.length;

                    if(visited[nx][ny][nd] > visited[x][y][d]+1){
                        visited[nx][ny][nd] = visited[x][y][d]+1;
                        q.offer(new Node(nx,ny,doorCount+1,nd));
                    }

                    nd = (d+mv.length-1)%mv.length;
                    if(visited[nx][ny][nd] > visited[x][y][d]+1){
                        visited[nx][ny][nd] = visited[x][y][d]+1;
                        q.offer(new Node(nx,ny,doorCount+1,nd));
                    }
                }
            }
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
