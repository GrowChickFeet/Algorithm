package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ4889_안정적인_문자열 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int caseNumber = 1;
        String input = "";
        while (!(input = reader.readLine()).startsWith("-")) builder.append(caseNumber++).append(". ").append(count(input)).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int count(String input) {
        int count = 0;

        int length = input.length();
        int open = 0;

        for (int i = 0; i < length; i++) {
            if (input.charAt(i) == '{') {
                if (length - i > open) open++;
                else {
                    count++;
                    open--;
                }
            } else {
                if (open > 0) open--;
                else {
                    count++;
                    open++;
                }
            }
        }

        return count;
    }

}
