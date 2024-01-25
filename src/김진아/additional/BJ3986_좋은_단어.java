package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Stack;

public class BJ3986_좋은_단어 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int count = 0;

        for (int i = 0; i < N; i++) {
            String word = reader.readLine();
            int length = word.length();
            if (length % 2 != 0) continue;

            Stack<Character> stack = new Stack<>();
            for (int j = 0; j < length; j++) {
                char character = word.charAt(j);
                if (!stack.isEmpty() && stack.peek() == character) stack.pop();
                else stack.push(character);
            }
            if (stack.isEmpty()) count++;
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
