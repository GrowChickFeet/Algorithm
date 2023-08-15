import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws  IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int maxLion = 0;
        int minLion = 0;

        int[] lions = new int[K];
        for (int i = 0; i < K; i++) {
            lions[i] = Integer.parseInt(reader.readLine());
            if (lions[i] > lions[maxLion]) maxLion = i;
            if (lions[i] < lions[minLion]) minLion = i;
        }

        int gap = 0;
        for (int i = 1; i < K; i++) gap += Math.abs(lions[i] - lions[i - 1]);

        int maxHeight = Integer.MIN_VALUE;
        int minHeight = Integer.MAX_VALUE;

        for (int i = K; i < N; i++) {
            int height = Integer.parseInt(reader.readLine());
            maxHeight = Math.max(height, maxHeight);
            minHeight = Math.min(height, minHeight);
        }

        if (maxHeight > lions[maxLion]) {
            int add = Math.min(Math.abs(lions[0] - maxHeight), Math.abs(lions[K - 1] - maxHeight));
            for (int i = 1; i < K; i++) add = Math.min(Math.abs(lions[i] - maxHeight) + Math.abs(lions[i - 1] - maxHeight) - Math.abs(lions[i] - lions[i - 1]), add);
            gap += add;
        }

        if (minHeight < lions[minLion]) {
            int add = Math.min(Math.abs(lions[0] - minHeight), Math.abs(lions[K - 1] - minHeight));
            for (int i = 1; i < K; i++) add = Math.min(Math.abs(lions[i] - minHeight) + Math.abs(lions[i - 1] - minHeight) - Math.abs(lions[i] - lions[i - 1]), add);
            gap += add;
        }

        writer.write(Integer.toString(gap));

        writer.close();
        reader.close();
    }

}
