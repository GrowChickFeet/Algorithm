package 김진아.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

public class BJ9536_여우는_어떻게_울지 {

    final static String QUESTION = "what does the fox say?";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            String[] sounds = reader.readLine().split(" ");
            HashSet<String> otherSounds = new HashSet<>();

            String input;
            while (!(input = reader.readLine()).equals(QUESTION)) {
                String[] otherSound = input.split(" ");
                otherSounds.add(otherSound[2]);
            }

            for (String sound : sounds) {
                if (!otherSounds.contains(sound)) builder.append(sound).append(" ");
            }
            builder.append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
