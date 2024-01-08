package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ16948_데스_나이트 {
  
    final static int DIRECTIONS = 6;
    final static int[] dx = { -2, -2, 0, 0, 2, 2 };
    final static int[] dy = { -1, 1, -2, 2, -1, 1 };
  
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        
        String[] input = reader.readLine().split(" ");
        int r1 = Integer.parseInt(input[0]);
        int c1 = Integer.parseInt(input[1]);
        int r2 = Integer.parseInt(input[2]);
        int c2 = Integer.parseInt(input[3]);
        
        writer.write(Integer.toString(distance(r1, c1, r2, c2)));

        writer.close();
        reader.close();
    }
    
    static int distance(int r1, int c1, int r2, int c2) {
        boolean[][] visited = new boolean[N][N];
        
        Queue<int[]> queue = new LinkedList<>();
        
        if (r1 == r2 && c1 == c2) return 0;
        visited[r1][c1] = true;
        queue.offer(new int[] { r1, c1, 0 });
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            
            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
                
                if (nx == r2 && ny == c2) return now[2] + 1;
                visited[nx][ny] = true;
                queue.offer(new int[] { nx, ny, now[2] + 1 });
            }
        }
        
        return -1;
    }

}
