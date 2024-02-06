package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.PriorityQueue;

public class BJ1417_국회의원_선거 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int vote = Integer.parseInt(reader.readLine());

        PriorityQueue<Integer> votes = new PriorityQueue<>((vote1, vote2) -> vote2 - vote1);
        for (int i = 0; i < N - 1; i++) votes.offer(Integer.parseInt(reader.readLine()));

        int count = 0;
        while (!votes.isEmpty() && votes.peek() >= vote) {
            votes.offer(votes.poll() - 1);
            vote++;
            count++;
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
