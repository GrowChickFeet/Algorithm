package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Stack;

public class BJ5397_키로거 {

    static Stack<Character> left;
    static Stack<Character> right;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int testcase = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testcase; i++) {
            String command = reader.readLine();

            left = new Stack<>();
            right = new Stack<>();

            int length = command.length();
            for (int j = 0; j < length; j++) execute(command.charAt(j));

            while (!left.isEmpty()) right.push(left.pop());
            while (!right.isEmpty()) builder.append(right.pop());
            builder.append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void execute(char command) {
        if (command == '-') {
            if (!left.isEmpty()) left.pop();
        } else if (command == '<') {
            if (!left.isEmpty()) right.push(left.pop());
        } else if (command == '>') {
            if (!right.isEmpty()) left.push(right.pop());
        } else left.push(command);
    }

}
