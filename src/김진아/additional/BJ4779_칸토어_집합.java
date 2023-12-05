package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ4779_칸토어_집합 {

    static StringBuilder builder;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        builder = new StringBuilder();

        String input;
        while ((input = reader.readLine()) != null && !input.isEmpty()) {
            cantor(Integer.parseInt(input));
            builder.append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void cantor(int N) {
        if (N == 0) builder.append("-");
        else {
            cantor(N - 1);
            int length = (int) Math.pow(3, N - 1);
            for (int i = 0; i < length; i++) builder.append(" ");
            cantor(N - 1);
        }
    }

}
