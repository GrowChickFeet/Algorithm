package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.PriorityQueue;

public class BJ13904_과제 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<int[]> works = new PriorityQueue<>((work1, work2) -> work2[0] - work1[0]);

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            works.offer(new int[] { Integer.parseInt(input[0]), Integer.parseInt(input[1]) });
        }

        int score = 0;
        for (int day = works.peek()[0]; day > 0; day--) {
            PriorityQueue<int[]> workable = new PriorityQueue<>((work1, work2) -> work2[1] - work1[1]);
            while (!works.isEmpty() && works.peek()[0] >= day) workable.offer(works.poll());
            if (!workable.isEmpty()) score += workable.poll()[1];
            while (!workable.isEmpty()) works.offer(workable.poll());
        }

        writer.write(Integer.toString(score));

        writer.close();
        reader.close();
    }

}
