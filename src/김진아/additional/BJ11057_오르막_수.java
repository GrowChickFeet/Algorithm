package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ11057_오르막_수 {

    final static int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        int[][] count = new int[N][10];
        for (int i = 0; i < 10; i++) count[0][i] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) count[i][j] = (count[i][j] + count[i - 1][k]) % MOD;
            }
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) sum = (sum + count[N - 1][i]) % MOD;

        writer.write(Integer.toString(sum));

        writer.close();
        reader.close();
    }

}
