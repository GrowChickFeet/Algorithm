import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    final static int MOD = 10007;

    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        count = new int[n + 1];
        count[0] = 1;
        count[1] = 2;

        writer.write(Integer.toString(count(n)));

        writer.close();
        reader.close();
    }

    static int count(int n) {
        if (count[n - 1] == 0) count[n - 1] = (count(n - 1) + count(n - 2)) % MOD;
        return count[n - 1];
    }

}
