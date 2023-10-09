package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1065_한수 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isValid(i)) count++;
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

    static boolean isValid(int number) {
        if (number < 100) return true;

        int pre = number % 10;
        int now = number / 10 % 10;
        int gap = now - pre;

        number /= 100;

        while (number > 0) {
            pre = now;
            now = number % 10;
            if (now - pre != gap) return false;
            number /= 10;
        }

        return true;
    }

}
