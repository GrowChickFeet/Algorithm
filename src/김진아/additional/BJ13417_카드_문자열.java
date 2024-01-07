package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ13417_카드_문자열 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine());
            String[] input = reader.readLine().split(" ");

            StringBuilder wordBuilder = new StringBuilder();
            char first = input[0].charAt(0);
            wordBuilder.append(first);

            for (int j = 1; j < N; j++) {
                char card = input[j].charAt(0);
                if (card > first) wordBuilder.append(card);
                else {
                    first = card;
                    wordBuilder.insert(0, card);
                }
            }

            builder.append(wordBuilder).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
