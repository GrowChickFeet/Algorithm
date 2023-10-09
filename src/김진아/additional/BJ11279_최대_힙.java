package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.PriorityQueue;

public class BJ11279_최대_힙 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(reader.readLine());
            if (command != 0) queue.offer(command);
            else {
                if (queue.isEmpty()) builder.append("0\n");
                else builder.append(queue.poll()).append("\n");
            }
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
