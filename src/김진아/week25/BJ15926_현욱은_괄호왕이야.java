package 김진아.week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Stack;

public class BJ15926_현욱은_괄호왕이야 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        String line = reader.readLine();

        Stack<Integer> opens = new Stack<>();
        int[] length = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (line.charAt(i) == '(') opens.push(i);
            else {
                if (opens.isEmpty()) continue;
                int open = opens.pop();
                length[i] = i - open + 1 + (open > 0 ? length[open - 1] : 0);
                max = Math.max(length[i], max);
            }
        }

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

}
