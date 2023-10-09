package 김진아.week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ2448_별_찍기_11 {

    final static int WIDTH = 6;
    final static int HEIGHT = 3;

    static boolean[][] star;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        star = new boolean[N][N / HEIGHT * WIDTH];

        star(0, 0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N / HEIGHT * WIDTH; j++) {
                if (star[i][j]) builder.append("*");
                else builder.append(" ");
            }
            builder.append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void star(int x, int y, int N) {
        if (N == HEIGHT) {
            star[x][y + 2] = true;
            star[x + 1][y + 1] = true;
            star[x + 1][y + 3] = true;
            for (int i = 0; i < WIDTH - 1; i++) star[x + 2][y + i] = true;
            return;
        }

        star(x, y + N / HEIGHT * WIDTH / 4, N / 2);
        star(x + N / 2, y, N / 2);
        star(x + N / 2, y + N / HEIGHT * WIDTH / 2, N / 2);
    }

}
