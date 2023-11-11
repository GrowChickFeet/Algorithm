package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ14500_테트로미노 {

    final static int SIZE = 4;
    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int N;
    static int M;
    static int[][] score;
    static boolean[][] visited;

    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        score = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) score[i][j] = Integer.parseInt(input[j]);
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) makeTetromino(i, j);
        }

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

    static void makeTetromino(int x, int y) {
        visited[x][y] = true;
        makeTetromino(x, y, score[x][y], 1, visited);
        visited[x][y] = false;

        if (x > 0 && x < N - 1 && y > 0) max = Math.max(score[x][y] + score[x - 1][y - 1] + score[x][y - 1] + score[x + 1][y - 1], max);
        if (x > 0 && x < N - 1 && y < M - 1) max = Math.max(score[x][y] + score[x - 1][y + 1] + score[x][y + 1] + score[x + 1][y + 1], max);
        if (x > 0 && y > 0 && y < M - 1) max = Math.max(score[x][y] + score[x - 1][y - 1] + score[x - 1][y] + score[x - 1][y + 1], max);
        if (x < N - 1 && y > 0 && y < M - 1) max = Math.max(score[x][y] + score[x + 1][y - 1] + score[x + 1][y] + score[x + 1][y + 1], max);
    }

    static void makeTetromino(int x, int y, int sum, int count, boolean[][] visited) {
        if (count == SIZE) {
            max = Math.max(sum, max);
            return;
        }

        for (int i = 0; i < DIRECTIONS; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            makeTetromino(nx, ny, sum + score[nx][ny], count + 1, visited);
            visited[nx][ny] = false;
        }
    }

}
