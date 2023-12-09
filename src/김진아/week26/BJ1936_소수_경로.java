package 김진아.week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ1936_소수_경로 {

    static int[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        isPrime = new int[10000];

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            String[] input = reader.readLine().split(" ");
            builder.append(count(Integer.parseInt(input[0]), Integer.parseInt(input[1]))).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int count(int start, int goal) {
        boolean[] visited = new boolean[10000];
        Queue<int[]> queue = new LinkedList<>();

        if (start == goal) return 0;
        visited[start] = true;
        queue.offer(new int[] { start, 0 });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 10; i++) {
                for (int j = 1; j <= 1000; j *= 10) {
                    int next = now[0] / (j * 10) * (j * 10) + i * j + now[0] % j;

                    if (next < 1000 || visited[next] || !isPrime(next)) continue;

                    if (next == goal) return now[1] + 1;
                    visited[next] = true;
                    queue.offer(new int[] { next, now[1] + 1 });
                }
            }
        }

        return -1;
    }

    static boolean isPrime(int number) {
        if (isPrime[number] == 1) return true;
        if (isPrime[number] == -1) return false;
        int mid = (int) Math.sqrt(number);
        if (mid * mid == number) return (isPrime[number] = -1) == 1;
        for (int i = 2; i < mid; i++) {
            if (number % i == 0) return (isPrime[number] = -1) == 1;
        }
        return (isPrime[number] = 1) == 1;
    }

}
