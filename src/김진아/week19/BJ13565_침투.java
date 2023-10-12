package 김진아.week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ13565_침투 {
    
    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };
    
    static int M;
    static int N;
    static boolean[][] matrix;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        
        matrix = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            String line = reader.readLine();
            for (int j = 0; j < N; j++) matrix[i][j] = line.charAt(j) == '0' ? true : false;
        }

        writer.write(connected() ? "YES" : "NO");

        writer.close();
        reader.close();
    }
    
    static boolean connected() {
        for (int i = 0; i < N; i++) {
            if (matrix[0][i] && connected(0, i)) return true;
        }
        return false;
    }
    
    static boolean connected(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        
        matrix[x][y] = false;
        queue.offer(new int[] { x, y });
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            
            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                
                if (nx < 0 || nx >= M || ny < 0 || ny >= N || !matrix[nx][ny]) continue;
                
                if (nx == M - 1) return true;
                
                matrix[nx][ny] = false;
                queue.offer(new int[] { nx, ny });
            }
        }
        
        return false;
    }

}
