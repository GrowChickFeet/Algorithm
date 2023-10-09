package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Stack;

public class BJ9935_문자열_폭발 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = reader.readLine();
        String bomb = reader.readLine();

        int length = str.length();
        int bombLength = bomb.length();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            stack.push(str.charAt(i));

            int size = stack.size();
            if (size < bombLength) continue;

            boolean willBomb = true;
            for (int j = 0; j < bombLength; j++) {
                if (stack.get(size - 1 - j) != bomb.charAt(bombLength - 1 - j)) {
                    willBomb = false;
                    break;
                }
            }

            if (willBomb) {
                for (int j = 0; j < bombLength; j++) stack.pop();
            }
        }

        if (stack.isEmpty()) writer.write("FRULA");
        else {
            StringBuilder builder = new StringBuilder();
            for (char character : stack) builder.append(character);
            writer.write(builder.toString());
        }

        writer.close();
        reader.close();
    }

}
