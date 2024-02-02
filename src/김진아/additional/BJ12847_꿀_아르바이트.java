package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ12847_꿀_아르바이트 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        long[] money = new long[n + 1];
        input = reader.readLine().split(" ");
        for (int i = 1; i <= n; i++) money[i] = money[i - 1] + Long.parseLong(input[i - 1]);

        long max = 0;
        for (int i = m; i <= n; i++) max = Math.max(money[i] - money[i - m], max);

        writer.write(Long.toString(max));

        writer.close();
        reader.close();
    }

}
