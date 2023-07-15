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
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(reader.readLine());

            int[] nums = new int[N];
            String[] input = reader.readLine().split(" ");
            for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(input[i]);

            long sum = 0;
            int max = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (nums[i] > max) max = nums[i];
                else sum += max - nums[i];
            }

            builder.append(sum).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
