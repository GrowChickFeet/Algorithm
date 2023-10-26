package 김진아.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ14569_시간표_짜기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        long[] classes = new long[N];
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            int k = Integer.parseInt(input[0]);

            long time = 0;
            for (int j = 1; j <= k; j++) time |= (1L << Integer.parseInt(input[j]));
            classes[i] = time;
        }

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) {
            String[] input = reader.readLine().split(" ");
            int p = Integer.parseInt(input[0]);

            long time = 0;
            for (int j = 1; j <= p; j++) time |= (1L << Integer.parseInt(input[j]));

            int count = 0;
            for (long classTime : classes) {
                if ((classTime & time) == classTime) count++;
            }
            builder.append(count).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }
}
