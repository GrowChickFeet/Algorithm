package 김진아.week09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ24418_알고리즘_수업_행렬_경로_문제_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());

        builder.append(count(N - 1, N - 1)).append(" ").append(N * N);

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int count(int x, int y) {
        int count = 0;
        if (x < 0 || y < 0) return 1;
        count += count(x - 1, y);
        count += count(x, y - 1);
        return count;
    }

}
