package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ9012_괄호 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            if (isValid(reader.readLine())) builder.append("YES\n");
            else builder.append("NO\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static boolean isValid(String str) {
        int length = str.length();
        int count = 0;

        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == '(') count++;
            else {
                if (--count < 0) return false;
            }
        }

        return count == 0;
    }

}
