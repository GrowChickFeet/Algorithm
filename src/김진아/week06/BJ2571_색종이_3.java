import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    final static int TOTAL_LENGTH = 100;
    final static int LENGTH = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] paper = new int[TOTAL_LENGTH][TOTAL_LENGTH];

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            int y = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            for (int j = 0; j < LENGTH; j++) {
                for (int k = 0; k < LENGTH; k++) paper[x + j][y + k] = 1;
            }
        }

        for (int i = 0; i < TOTAL_LENGTH; i++) {
            for (int j = 1; j < TOTAL_LENGTH; j++) {
                if (paper[i][j] != 0) paper[i][j] += paper[i][j - 1];
            }
        }

        int max = 0;
        for (int i = 0; i < TOTAL_LENGTH; i++) {
            for (int j = 0; j < TOTAL_LENGTH; j++) {
                if (paper[i][j] == 0) continue;
                int width = TOTAL_LENGTH;
                for (int k = i; k < TOTAL_LENGTH; k++) {
                    if (paper[k][j] == 0) break;
                    width = Math.min(paper[k][j], width);
                    max = Math.max(width * (k - i + 1), max);
                }
            }
        }

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

}
