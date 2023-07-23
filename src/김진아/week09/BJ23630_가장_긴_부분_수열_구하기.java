import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] bits = new int[20];

        int N = Integer.parseInt(reader.readLine());
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(input[i]);
            int bit = 0;
            while (num > 0) {
                bits[bit++] += num % 2;
                num /= 2;
            }
        }

        int max = 0;
        for (int i = 0; i < 20; i++) max = Math.max(bits[i], max);

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

}
