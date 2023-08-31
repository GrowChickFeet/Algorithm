import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);
            if (abs1 == abs2) return o1 - o2;
            return abs1 - abs2;
        });

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(reader.readLine());
            if (input != 0) queue.offer(input);
            else {
                int min = queue.isEmpty() ? 0 : queue.poll();
                builder.append(min).append("\n");
            }
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
