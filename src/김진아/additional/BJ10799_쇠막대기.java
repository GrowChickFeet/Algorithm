package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ10799_쇠막대기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = reader.readLine();
        int length = input.length();

        int count = 0;
        int bars = 0;
        for (int i = 0; i < length - 1; i++) {
            if (input.charAt(i) == ')') {
                bars--;
                count++;
            } else if (input.charAt(i + 1) == ')') {
                count += bars;
                i++;
            } else bars++;
        }
        count += bars;

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
