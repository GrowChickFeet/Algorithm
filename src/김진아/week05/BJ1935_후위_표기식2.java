import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        String expression = reader.readLine();
        int length = expression.length();

        double[] nums = new double[N];
        for (int i = 0; i < N; i++) nums[i] = Double.parseDouble(reader.readLine());

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char now = expression.charAt(i);

            if (now >= 'A' && now <= 'Z') {
                stack.push(nums[now - 'A']);
                continue;
            }

            double num2 = stack.pop();
            double num1 = stack.pop();

            if (now == '+') stack.push(num1 + num2);
            else if (now == '-') stack.push(num1 - num2);
            else if (now == '*') stack.push(num1 * num2);
            else if (now == '/') stack.push(num1 / num2);
        }

        writer.write(String.format("%.2f", stack.pop()));

        writer.close();
        reader.close();
    }

}
