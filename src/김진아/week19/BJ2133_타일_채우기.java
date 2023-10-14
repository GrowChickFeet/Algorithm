package 김진아.week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ2133_타일_채우기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        int[] count = new int[N + 1];
        count[0] = 1;
        for (int i = 2; i <= N; i += 2) {
            count[i] = count[i - 2] * 3;
            for (int j = 4; j <= i; j += 2) count[i] += count[i - j] * 2;
        }

        writer.write(Integer.toString(count[N]));

        writer.close();
        reader.close();
    }

}
