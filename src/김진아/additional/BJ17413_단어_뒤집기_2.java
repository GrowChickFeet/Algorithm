package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Stack;

public class BJ17413_단어_뒤집기_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String S = reader.readLine();
        int length = S.length();

        Stack<Character> stack = new Stack<>();
        boolean open = false;

        for (int i = 0; i < length; i++) {
            char character = S.charAt(i);

            if (character == '<') {
                while (!stack.isEmpty()) builder.append(stack.pop());
                open = true;
            }

            if (open) {
                builder.append(character);
                if (character == '>') open = false;
                continue;
            }

            if (character == ' ') {
                while (!stack.isEmpty()) builder.append(stack.pop());
                builder.append(character);
                continue;
            }

            stack.push(character);
        }

        while (!stack.isEmpty()) builder.append(stack.pop());

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
