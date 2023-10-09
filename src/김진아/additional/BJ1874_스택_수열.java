package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Stack;

public class BJ1874_스택_수열 {

    static int n;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(reader.readLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(reader.readLine());

        writer.write(makeNums());

        writer.close();
        reader.close();
    }

    static String makeNums() {
        StringBuilder builder = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        int num = 1;

        for (int i = 0; i < n; i++) {
            while (num <= nums[i]) {
                stack.push(num++);
                builder.append("+\n");
            }

            if (stack.pop() != nums[i]) return "NO";
            builder.append("-\n");
        }

        return builder.toString();
    }

}
