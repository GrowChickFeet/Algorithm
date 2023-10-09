package 김진아.week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.PriorityQueue;

public class BJ15903_카드_합체_놀이 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        PriorityQueue<Long> queue = new PriorityQueue<>();
        input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) queue.offer(Long.parseLong(input[i]));

        for (int i = 0; i < m; i++) {
            long result = queue.poll() + queue.poll();
            queue.offer(result);
            queue.offer(result);
        }

        long sum = 0;
        while (!queue.isEmpty()) sum += queue.poll();

        writer.write(Long.toString(sum));

        writer.close();
        reader.close();
    }

}
