import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    final static int TOTAL_SIZE = 101;
    final static int SIZE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        int[][] paper = new int[TOTAL_SIZE][TOTAL_SIZE];
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            for (int j = 0; j < SIZE; j++) {
                paper[x + j][y]++;
                paper[x + j][y + SIZE]--;
            }
        }

        for (int i = 0; i < TOTAL_SIZE; i++) {
            for (int j = 1; j < TOTAL_SIZE; j++) paper[i][j] += paper[i][j - 1];
        }

        int count = 0;
        for (int i = 0; i < TOTAL_SIZE; i++) {
            for (int j = 0; j < TOTAL_SIZE; j++) {
                if (paper[i][j] > 0) count++;
            }
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
