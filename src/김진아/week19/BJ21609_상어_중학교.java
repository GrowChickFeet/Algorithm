package 김진아.week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BJ21609_상어_중학교 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int N;
    static Integer[][] matrix;

    static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);

        matrix = new Integer[N][N];
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) matrix[i][j] = Integer.parseInt(input[j]);
        }

        score = 0;
        play();

        writer.write(Integer.toString(score));

        writer.close();
        reader.close();
    }

    static void play() {
        int now = 0;
        while ((now = score()) != 0) {
            score += now;
            move();
        }
    }

    static int score() {
        ArrayList<int[]> groups = new ArrayList<>();
        boolean[][] selected = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == null || matrix[i][j] <= 0 || selected[i][j]) continue;

                int[] group = { 0, 0, i, j, matrix[i][j] };

                boolean[][] selectedRainbow = new boolean[N][N];
                Queue<int[]> queue = new LinkedList<>();

                group[0]++;
                selected[i][j] = true;
                queue.offer(new int[] { i, j });

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();

                    for (int k = 0; k < DIRECTIONS; k++) {
                        int nx = now[0] + dx[k];
                        int ny = now[1] + dy[k];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || selected[nx][ny] || selectedRainbow[nx][ny] || matrix[nx][ny] == null || (matrix[nx][ny] != 0 && !matrix[nx][ny].equals(matrix[i][j]))) continue;

                        group[0]++;
                        if (matrix[nx][ny] == 0) group[1]++;
                        if (matrix[nx][ny] != 0) selected[nx][ny] = true;
                        else selectedRainbow[nx][ny] = true;
                        queue.offer(new int[] { nx, ny });
                    }
                }

                if (group[0] >= 2) groups.add(group);
            }
        }

        if (groups.isEmpty()) return 0;

        groups.sort((group1, group2) -> {
            if (group1[0] < group2[0]) return 1;
            if (group1[0] > group2[0]) return -1;

            if (group1[1] < group2[1]) return 1;
            if (group1[1] > group2[1]) return -1;

            if (group1[2] < group2[2]) return 1;
            if (group1[2] > group2[2]) return -1;

            return group2[3] - group1[3];
        });

        int[] target = groups.get(0);
        remove(target[2], target[3], target[4]);
        return (int) Math.pow(target[0], 2);
    }

    static void remove(int x, int y, int color) {
        Queue<int[]> queue = new LinkedList<>();

        matrix[x][y] = null;
        queue.offer(new int[] { x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || matrix[nx][ny] == null || (matrix[nx][ny] != 0 && matrix[nx][ny] != color)) continue;

                matrix[nx][ny] = null;
                queue.offer(new int[] { nx, ny });
            }
        }
    }

    static void move() {
        gravity();
        rotate();
        gravity();
    }

    static void gravity() {
        for (int i = 0; i < N; i++) {
            int empty = -1;
            for (int j = N - 1; j >= 0; j--) {
                if (matrix[j][i] == null) {
                    if (empty == -1) empty = j;
                } else if (matrix[j][i] == -1) empty = -1;
                else if (empty != -1) {
                    matrix[empty][i] = matrix[j][i];
                    matrix[j][i] = null;
                    empty--;
                }
            }
        }
    }

    static void rotate() {
        Integer[][] rotated = new Integer[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) rotated[i][j] = matrix[j][N - 1 - i];
        }
        matrix = rotated;
    }

}
