package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ1966_프린터_큐 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            String[] input = reader.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);

            input = reader.readLine().split(" ");
            Queue<int[]> queue = new LinkedList<>();
            int[] priorityList = new int[N];
            for (int j = 0; j < N; j++) {
                int priority = Integer.parseInt(input[j]);
                priorityList[j] = priority;
                queue.offer(new int[] { j, priority });
            }

            Arrays.sort(priorityList);

            int order = 1;
            int priorityIndex = N - 1;
            int[] doc = queue.poll();
            while (doc[0] != M || doc[1] != priorityList[priorityIndex]) {
                if (doc[1] != priorityList[priorityIndex]) queue.offer(doc);
                else {
                    order++;
                    priorityIndex--;
                }
                doc = queue.poll();
            }

            builder.append(order).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
