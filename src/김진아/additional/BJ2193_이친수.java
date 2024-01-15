package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ2193_이친수 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        long[][] count = new long[N + 1][2];
        count[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            count[i][0] = count[i - 1][0] + count[i - 1][1];
            count[i][1] = count[i - 1][0];
        }

        writer.write(Long.toString(count[N][0] + count[N][1]));

        writer.close();
        reader.close();
    }

}
