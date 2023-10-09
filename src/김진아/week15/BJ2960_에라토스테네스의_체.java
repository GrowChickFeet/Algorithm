package 김진아.week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ2960_에라토스테네스의_체 {

    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        writer.write(Integer.toString(deletion()));

        writer.close();
        reader.close();
    }

    static int deletion() {
        boolean[] deleted = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            if (deleted[i]) continue;
            for (int j = i; j <= N; j += i) {
                if (deleted[j]) continue;
                deleted[j] = true;
                if (--K == 0) return j;
            }
        }
        return -1;
    }

}
