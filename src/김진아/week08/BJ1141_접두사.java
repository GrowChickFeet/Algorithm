import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ1141_접두사 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) words[i] = reader.readLine();

        Arrays.sort(words, (word1, word2) -> word2.length() - word1.length());

        boolean[] selected = new boolean[N];
        selected[0] = true;
        int count = 1;

        for (int i = 1; i < N; i++) {
            boolean prefix = false;

            for (int j = 0; j < i; j++) {
                if (!words[j].startsWith(words[i])) continue;
                prefix = true;
                break;
            }

            if (prefix) continue;
            selected[i] = true;
            count++;
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
