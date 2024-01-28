package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class BJ12761_돌다리 {

    final static int LENGTH = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        int N = Integer.parseInt(input[2]);
        int M = Integer.parseInt(input[3]);

        writer.write(Integer.toString(time(A, B, N, M)));

        writer.close();
        reader.close();
    }

    static int time(int A, int B, int N, int M) {
        boolean[] visited = new boolean[LENGTH + 1];
        int[] moving = { 1, -1, A, -A, B, -B, A, B };
        int directions = 8;
        int plus = 6;

        Queue<int[]> queue = new LinkedList<>();

        visited[N] = true;
        if (N == M) return 0;
        queue.offer(new int[] { N, 0 });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < directions; i++) {
                int next = now[0];
                if (i < plus) next += moving[i];
                else next *= moving[i];

                if (next < 0 || next > LENGTH || visited[next]) continue;

                visited[next] = true;
                if (next == M) return now[1] + 1;
                queue.offer(new int[] { next, now[1] + 1 });
            }
        }

        return -1;
    }

}
