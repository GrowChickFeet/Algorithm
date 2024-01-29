package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ14248_점프_점프 {

    static int n;
    static int[] stones;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(reader.readLine());
        stones = new int[n];

        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) stones[i] = Integer.parseInt(input[i]);

        int start = Integer.parseInt(reader.readLine()) - 1;

        writer.write(Integer.toString(count(start)));

        writer.close();
        reader.close();
    }

    static int count(int start) {
        boolean[] visited = new boolean[n];
        int count = 0;

        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        count++;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            int next = now + stones[now];
            if (next >= 0 && next < n && !visited[next]) {
                visited[next] = true;
                count++;
                queue.offer(next);
            }

            next = now - stones[now];
            if (next >= 0 && next < n && !visited[next]) {
                visited[next] = true;
                count++;
                queue.offer(next);
            }
        }

        return count;
    }

}
