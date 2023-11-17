package 김진아.week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;

public class BJ1464_뒤집기_3 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String S = reader.readLine();
        int length = S.length();

        LinkedList<Character> flip = new LinkedList<>();
        char first = S.charAt(0);
        flip.addFirst(first);
        for (int i = 1; i < length; i++) {
            char character = S.charAt(i);
            if (character > first) flip.addLast(character);
            else {
                first = character;
                flip.addFirst(first);
            }
        }

        for (Character character : flip) builder.append(character);
        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
