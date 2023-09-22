import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

public class Main {

    static HashSet<String> phones;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int t = Integer.parseInt(reader.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine());

            phones = new HashSet<>();
            for (int j = 0; j < n; j++) phones.add(reader.readLine());

            if (isValid()) builder.append("YES").append("\n");
            else builder.append("NO").append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static boolean isValid() {
        for (String phone : phones) {
            int length = phone.length();
            for (int i = 1; i < length; i++) {
                if (phones.contains(phone.substring(0, i))) return false;
            }
        }
        return true;
    }

}
