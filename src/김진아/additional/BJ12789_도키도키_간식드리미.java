package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Stack;

public class BJ12789_도키도키_간식드리미 {

    static int N;
    static int[] students;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        students = new int[N];

        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) students[i] = Integer.parseInt(input[i]);

        writer.write(happySnack() ? "Nice" : "Sad");

        writer.close();
        reader.close();
    }

    static boolean happySnack() {
        Stack<Integer> stack = new Stack<>();
        int now = 1;

        for (int i = 0; i < N; i++) {
            if (students[i] != now) {
                if (!stack.isEmpty() && stack.peek() < students[i]) return false;
                stack.push(students[i]);
                continue;
            }

            now++;
            while (!stack.isEmpty() && stack.peek() == now) {
                now++;
                stack.pop();
            }
        }

        return now == N + 1 && stack.isEmpty();
    }

}
