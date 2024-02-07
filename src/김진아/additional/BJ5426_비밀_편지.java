package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ5426_비밀_편지 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int testCase = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testCase; i++) builder.append(decode(reader.readLine())).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static String decode(String code) {
        int length = code.length();
        int size = (int) Math.sqrt(length);

        StringBuilder decoder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) decoder.append(code.charAt(j * size + size - 1 - i));
        }

        return decoder.toString();
    }

}
