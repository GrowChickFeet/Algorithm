package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.TreeSet;
import java.util.Stack;

public class BJ17178_줄서기 {

    static Integer[] fans;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        fans = new Integer[N * 5];
        for (int i = 0; i < N; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < 5; j++) fans[i * 5 + j] = (line[j].charAt(0) - 'A') * 10000 + Integer.parseInt(line[j].substring(2));
        }

        writer.write(valid() ? "GOOD" : "BAD");

        writer.close();
        reader.close();
    }

    static boolean valid() {
        TreeSet<Integer> remaining = new TreeSet<>();
        for (int fan : fans) remaining.add(fan);

        Stack<Integer> waiting = new Stack<>();
        for (int fan : fans) {
            while (!waiting.isEmpty() && waiting.peek().equals(remaining.first())) remaining.remove(waiting.pop());
            if (fan == remaining.first()) remaining.remove(fan);
            else {
                if (!waiting.isEmpty() && waiting.peek().compareTo(fan) < 0) return false;
                waiting.push(fan);
            }
        }

        return true;
    }

}
