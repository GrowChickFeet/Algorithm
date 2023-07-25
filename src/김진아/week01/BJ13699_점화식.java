import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    static int n;
    static long[] t;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(reader.readLine());
        t = new long[n + 1];

        t[0] = 1;
        writer.write(Long.toString(t(n)));

        writer.close();
        reader.close();
    }

    static long t(int num) {
        if (t[num] == 0) {
            for (int i = 0; i < num; i++) t[num] += t(i) * t(num - 1 - i);
        }
        return t[num];
    }

}
