package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ2011_암호코드 {

    final static int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(Integer.toString(count(reader.readLine())));

        writer.close();
        reader.close();
    }

    static int count(String code) {
        int length = code.length();
        int[] count = new int[length + 1];
        count[0] = 1;
        count[1] = 1;

        int number = code.charAt(0) - '0';
        if (number == 0) return 0;

        for (int i = 2; i <= length; i++) {
            count[i] = (count[i] + count[i - 1]) % MOD;
            number = number * 10 + code.charAt(i - 1) - '0';
            if (number % 10 == 0) {
                if (number < 1 || number > 26) return 0;
                count[i] = count[i - 2];
            } else if (number >= 10 && number <= 26) count[i] = (count[i] + count[i - 2]) % MOD;
            number %= 10;
        }

        return count[length];
    }

}
