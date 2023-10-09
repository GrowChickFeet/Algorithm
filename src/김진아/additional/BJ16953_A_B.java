package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

class Stat {

    long num;
    int count;

    public Stat(long num, int count) {
        this.num = num;
        this.count = count;
    }

}

public class BJ16953_A_B {

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
        Queue<Stat> queue = new LinkedList<>();

        queue.offer(new Stat(A, 1));

        while (!queue.isEmpty()) {
            Stat now = queue.poll();

            long next = now.num * 2;
            if (next == B) return now.count + 1;
            if (next < B) queue.offer(new Stat(next, now.count + 1));

            next = now.num * 10 + 1;
            if (next == B) return now.count + 1;
            if (next < B) queue.offer(new Stat(next, now.count + 1));
        }

        return -1;
    }

}
