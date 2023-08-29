import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

class Status {

    long num;
    int count;

    public Status(long num, int count) {
        this.num = num;
        this.count = count;
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);

        writer.write(Integer.toString(count(A, B)));

        writer.close();
        reader.close();
    }

    static int count(int A, int B) {
        Queue<Status> queue = new LinkedList<>();

        queue.offer(new Status(A, 1));

        while (!queue.isEmpty()) {
            Status now = queue.poll();

            long next = now.num * 2;
            if (next == B) return now.count + 1;
            if (next < B) queue.offer(new Status(next, now.count + 1));

            next = now.num * 10 + 1;
            if (next == B) return now.count + 1;
            if (next < B) queue.offer(new Status(next, now.count + 1));
        }

        return -1;
    }

}
