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

        Stack<Integer> nums = new Stack<>();

        int K = Integer.parseInt(reader.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(reader.readLine());
            if (num == 0) nums.pop();
            else nums.push(num);
        }

        int sum = 0;
        for (int num : nums) sum += num;

        writer.write(Integer.toString(sum));

        writer.close();
        reader.close();
    }

}
