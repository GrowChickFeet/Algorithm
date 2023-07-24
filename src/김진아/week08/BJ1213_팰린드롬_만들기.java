import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String name = reader.readLine();

        writer.write(getPalindrome(name));

        writer.close();
        reader.close();
    }

    static String getPalindrome(String origin) {
        int length = origin.length();
        int[] count = new int[26];
        for (int i = 0; i < length; i++) count[origin.charAt(i) - 'A']++;

        int center = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                if (center != -1) return "I'm Sorry Hansoo";
                center = i;
            }
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) continue;
            for (int j = 0; j < count[i] / 2; j++) builder.append((char) ('A' + i));
            count[i] /= 2;
        }

        if (center != -1) builder.append((char) ('A' + center));

        for (int i = 25; i >= 0; i--) {
            if (count[i] == 0) continue;
            for (int j = 0; j < count[i]; j++) builder.append((char) ('A' + i));
            count[i] /= 2;
        }

        return builder.toString();
    }

}
