package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1769_3의_배수 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String number = reader.readLine();
        int length = number.length();

        int now = 0;
        for (int i = 0; i < length; i++) now += number.charAt(i) - '0';

        int count = length > 1 ? 1 : 0;
        length = (int) (Math.log(now) / Math.log(10));

        while (length > 0) {
            int next = 0;
            for (; now > 0; now /= 10) next += now % 10;
            now = next;
            length = (int) (Math.log(now) / Math.log(10));
            count++;
        }

        writer.write(new StringBuilder().append(count).append("\n").append(now % 3 == 0 ? "YES" : "NO").toString());

        writer.close();
        reader.close();
    }

}
