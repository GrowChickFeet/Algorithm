import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String S = reader.readLine();
        int length = S.length();

        String[] tails = new String[length];
        for (int i = 0; i < length; i++) tails[i] = S.substring(i);

        Arrays.sort(tails);

        for (String tail : tails) builder.append(tail).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
