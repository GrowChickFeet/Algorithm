import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(reader.readLine());

            int[] files = new int[K + 1];
            String[] input = reader.readLine().split(" ");
            for (int j = 1; j <= K; j++) files[j] = Integer.parseInt(input[j - 1]);

            int[] sum = new int[K + 1];
            for (int j = 1; j <= K; j++) sum[j] = sum[j - 1] + files[j];

            int[][] min = new int[K + 1][K + 1];
            for (int width = 1; width < K; width++) {
                for (int start = 1; start + width <= K; start++) {
                    int end = start + width;
                    min[start][end] = Integer.MAX_VALUE;
                    for (int mid = start; mid < end; mid++) min[start][end] = Math.min(min[start][mid] + min[mid + 1][end] + sum[end] - sum[start - 1], min[start][end]);
                }
            }

            builder.append(min[1][K]).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
