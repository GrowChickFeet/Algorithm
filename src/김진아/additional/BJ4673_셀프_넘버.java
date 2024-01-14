package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ4673_셀프_넘버 {

    final static int MAX = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        boolean[] existConstructor = new boolean[MAX + 1];
        for (int i = 1; i <= MAX; i++) {
            int result = d(i);
            if (result <= MAX) existConstructor[result] = true;
        }

        for (int i = 1; i <= MAX; i++) {
            if (!existConstructor[i]) builder.append(i).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int d(int n) {
        int result = n;
        for (; n > 0; n /= 10) result += n % 10;
        return result;
    }

}
