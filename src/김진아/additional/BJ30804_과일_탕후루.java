package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ30804_과일_탕후루 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int[] fruits = new int[N];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) fruits[i] = Integer.parseInt(input[i]);

        int[] count = new int[10];

        int start = 0;
        int type = 0;
        int max = 0;

        for (int end = 0; end < N; end++) {
            if (count[fruits[end]]++ == 0) type++;
            while (type > 2) {
                if (--count[fruits[start++]] == 0) type--;
            }
            max = Math.max(end - start + 1, max);
        }

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

}
