package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1780_종이의_개수 {

    static int N;
    static int[][] paper;

    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) paper[i][j] = Integer.parseInt(input[j]);
        }

        count();

        writer.write(new StringBuilder()
                    .append(count[0]).append("\n")
                    .append(count[1]).append("\n")
                    .append(count[2]).toString());

        writer.close();
        reader.close();
    }

    static void count() {
        count = new int[3];
        count(N, 0, 0);
    }

    static void count(int n, int x1, int y1) {
        boolean same = true;
        int size = n * n;

        for (int i = 0; i < size; i++) {
            if (paper[x1 + i / n][y1 + i % n] == paper[x1][y1]) continue;
            same = false;
            break;
        }

        if (same) count[paper[x1][y1] + 1]++;
        else {
            n /= 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) count(n, x1 + n * i, y1 + n * j);
            }
        }
    }

}
