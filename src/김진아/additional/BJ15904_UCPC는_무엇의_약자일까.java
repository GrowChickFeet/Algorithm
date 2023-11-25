package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ15904_UCPC는_무엇의_약자일까 {

    final static int LENGTH = 4;
    final static String UCPC = "UCPC";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(isUCPC(reader.readLine()) ? "I love UCPC" : "I hate UCPC");

        writer.close();
        reader.close();
    }

    static boolean isUCPC(String word) {
        int length = word.length();
        int index = 0;
        char now = UCPC.charAt(index);
        for (int i = 0; i < length; i++) {
            if (word.charAt(i) != now) continue;
            if (++index >= LENGTH) return true;
            now = UCPC.charAt(index);
        }
        return false;
    }

}
