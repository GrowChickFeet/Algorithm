package 김진아.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ20544_공룡게임 {

    final static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        long[][] count = new long[N + 3][2];
        count[1][0] = 1;
        count[2][0] = 2;
        count[2][1] = 1;
        count[3][0] = 4;
        count[3][1] = 4;

        for (int i = 4; i <= N; i++) {
            count[i][0] = (count[i - 1][0] + count[i - 2][0] + count[i - 3][0]) % MOD;
            count[i][1] = (count[i - 2][0] + count[i - 3][0] * 2 + count[i - 1][1] + count[i - 2][1] * 2 + count[i - 3][1] * 3) % MOD;
        }

        writer.write(Long.toString(count[N][1]));

        writer.close();
        reader.close();
    }

}
