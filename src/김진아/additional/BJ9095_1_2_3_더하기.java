import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());

        count = new int[11];
        count[1] = 1;
        count[2] = 2;
        count[3] = 4;

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(reader.readLine());
            builder.append(count(n)).append("\n");
        }

        Float f = 0.1f;
        Float f2 = 0.2f;
        boolean s = f.equals(f2);
        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int count(int n) {
        if (count[n] == 0) count[n] = count(n - 1) + count(n - 2) + count(n - 3);
        return count[n];
    }

}
