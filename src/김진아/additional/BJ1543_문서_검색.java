package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1543_문서_검색 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String docs = reader.readLine();
        String target = reader.readLine();

        int docsLength = docs.length();
        int targetLength = target.length();

        int count = 0;
        for (int i = 0; i <= docsLength - targetLength; i++) {
            boolean found = true;
            for (int j = 0; j < targetLength; j++) {
                if (docs.charAt(i + j) != target.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                count++;
                i += targetLength - 1;
            }
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
