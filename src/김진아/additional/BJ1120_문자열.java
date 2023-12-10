package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1120_문자열 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");

        int length1 = input[0].length();
        int length2 = input[1].length();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= length2 - length1; i++) {
            int count = 0;
            for (int j = 0; j < length1; j++) {
                if (input[0].charAt(j) != input[1].charAt(i + j)) count++;
            }
            min = Math.min(count, min);
        }

        writer.write(Integer.toString(min));

        writer.close();
        reader.close();
    }

}
