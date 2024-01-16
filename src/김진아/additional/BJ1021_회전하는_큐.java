package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayDeque;

public class BJ1021_회전하는_큐 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) deque.offer(i);

        int[] nums = new int[M];
        input = reader.readLine().split(" ");
        for (int i = 0; i < M; i++) nums[i] = Integer.parseInt(input[i]);

        int count = 0;

        for (int num : nums) {
            int moving = 0;
            int first;

            while ((first = deque.poll()) != num) {
                deque.offer(first);
                moving++;
            }

            if (moving > N / 2) moving = N - moving;
            count += moving;
            N--;
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
