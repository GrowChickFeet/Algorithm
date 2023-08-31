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

        boolean[][] guitars = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) guitars[i][j] = input[1].charAt(j) == 'Y' ? true : false;
        }

        int[] max = { 0, -1 };
        for (int i = 1; i < (1 << N); i++) {
            boolean[] play = new boolean[M];
            int count = 0;

            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) == 0) continue;
                for (int k = 0; k < M; k++) play[k] |= guitars[j][k];
                count++;
            }

            int song = 0;
            for (int j = 0; j < M; j++) {
                if (play[j]) song++;
            }

            if (song > max[0] || (song == max[0] && count < max[1])) max = new int[] { song, count };
        }

        writer.write(Integer.toString(max[1]));

        writer.close();
        reader.close();
    }

}
