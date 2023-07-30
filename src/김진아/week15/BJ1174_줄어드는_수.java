import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        writer.write(Long.toString(decreasing(N)));

        writer.close();
        reader.close();
    }

    static long decreasing(int N) {
        if (N <= 10) return N - 1;

        Queue<Long> queue = new LinkedList<>();
        int count = 10;

        for (long i = 1; i < 10; i++) queue.offer(i);

        while (!queue.isEmpty()) {
            long pre = queue.poll();

            long last = pre % 10;
            for (int i = 0; i < last; i++) {
                long now = pre * 10 + i;
                if (++count == N) return now;
                queue.offer(now);
            }
        }

        return -1;
    }

}
