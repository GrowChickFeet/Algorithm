import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = reader.readLine();
        int length = str.length();

        HashSet<String> pieces = new HashSet();
        for (int i = 0; i < length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = i; j < length; j++) {
                builder.append(str.charAt(j));
                pieces.add(builder.toString());
            }
        }

        writer.write(Integer.toString(pieces.size()));

        writer.close();
        reader.close();
    }

}
