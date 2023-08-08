import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] chapters = new int[M + 1][2];
        for (int i = 1; i <= M; i++) {
            input = reader.readLine().split(" ");
            chapters[i][0] = Integer.parseInt(input[0]);
            chapters[i][1] = Integer.parseInt(input[1]);
        }

        int[][] max = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                if (j < chapters[i][0]) max[i][j] = max[i - 1][j];
                else max[i][j] = Math.max(max[i - 1][j - chapters[i][0]] + chapters[i][1], max[i - 1][j]);
            }
        }

        writer.write(Integer.toString(max[M][N]));

        writer.close();
        reader.close();
    }

}
