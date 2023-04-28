package 김현욱.week7;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2665_미로만들기 {
    public static int n;
    public static int[][] mv = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static class Node {
        int x, y, count, dist;

        public Node(int x, int y, int count, int dist) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[][] maze = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                maze[i][j] = line[j] - '0';
            }
        }

        Node[][] visited = new Node[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = new Node(i,j,Integer.MAX_VALUE,Integer.MAX_VALUE);
            }
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0,0,0));
        visited[0][0].count=0;
        visited[0][0].dist=0;
        int result = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            Node node = q.poll();

            int x = node.x;
            int y = node.y;
            int count = node.count;
            int dist = node.dist;

            if(x==n-1&&y==n-1) {
                result = Math.min(result,count);
                continue;
            }

            for(int d=0;d<mv.length;d++){
                int nx = x+mv[d][0];
                int ny = y+mv[d][1];

                if(!isIn(nx,ny)) continue;

                //흰블록
                if(maze[nx][ny]==1){
                    if(visited[nx][ny].count > count){
                        visited[nx][ny].count = count;
                        visited[nx][ny].dist = dist+1;
                        q.offer(new Node(nx,ny,count,dist+1));
                    }
                    else if(visited[nx][ny].count == count && visited[nx][ny].dist > dist+1){
                        visited[nx][ny].dist = dist+1;
                        q.offer(new Node(nx,ny,count,dist+1));
                    }
                }
                else{//검은 블록
                    if(visited[nx][ny].count > count+1){
                        visited[nx][ny].count = count+1;
                        visited[nx][ny].dist = dist+1;
                        q.offer(new Node(nx,ny,count+1,dist+1));
                    }
                    else if(visited[nx][ny].count == count+1 && visited[nx][ny].dist > dist+1) {
                        visited[nx][ny].dist = dist + 1;
                        q.offer(new Node(nx, ny, count + 1, dist + 1));
                    }
                }
            }
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
