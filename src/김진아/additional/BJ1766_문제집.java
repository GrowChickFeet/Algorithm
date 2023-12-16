package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Problem {

    int number;
    ArrayList<Problem> pre;
    ArrayList<Problem> next;

    public Problem(int number) {
        this.number = number;
        pre = new ArrayList<>();
        next = new ArrayList<>();
    }

}

public class BJ1766_문제집 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        Problem[] problems = new Problem[N + 1];
        for (int i = 1; i <= N; i++) problems[i] = new Problem(i);

        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int pre = Integer.parseInt(input[0]);
            int next = Integer.parseInt(input[1]);
            problems[pre].next.add(problems[next]);
            problems[next].pre.add(problems[pre]);
        }

        PriorityQueue<Problem> queue = new PriorityQueue<>((problem1, problem2) -> problem1.number - problem2.number);

        for (int i = 1; i <= N; i++) {
            if (problems[i].pre.isEmpty()) queue.offer(problems[i]);
        }

        while (!queue.isEmpty()) {
            Problem now = queue.poll();
            builder.append((now.number)).append(" ");

            for (Problem next : now.next) {
                next.pre.remove(now);
                if (next.pre.isEmpty()) queue.offer(next);
            }
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
