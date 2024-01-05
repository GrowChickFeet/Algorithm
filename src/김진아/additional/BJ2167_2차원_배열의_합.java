package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ2167_2차원_배열의_합 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] board = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            input = reader.readLine().split(" ");
            for (int j = 1; j <= M; j++) board[i][j] = Integer.parseInt(input[j - 1]);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) board[i][j] += board[i - 1][j] + board[i][j - 1] - board[i - 1][j - 1];
        }

        int K = Integer.parseInt(reader.readLine());
        for (int i = 0; i < K; i++) {
            input = reader.readLine().split(" ");
            int x1 = Integer.parseInt(input[0]);
            int y1 = Integer.parseInt(input[1]);
            int x2 = Integer.parseInt(input[2]);
            int y2 = Integer.parseInt(input[3]);

            builder.append(board[x2][y2] - board[x1 - 1][y2] - board[x2][y1 - 1] + board[x1 - 1][y1 - 1]).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
