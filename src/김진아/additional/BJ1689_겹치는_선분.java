import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.PriorityQueue;

public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

            PriorityQueue<int[]> points = new PriorityQueue<>((point1, point2) -> {
                if (point1[0] == point2[0]) return point1[1] - point2[1];
                return point1[0] - point2[0];
            });

            int N = Integer.parseInt(reader.readLine());
            for (int i = 0; i < N; i++) {
                String[] input = reader.readLine().split(" ");
                points.offer(new int[] { Integer.parseInt(input[0]), 1 });
                points.offer(new int[] { Integer.parseInt(input[1]), -1 });
            }

            int max = 0;
            int count = 0;
            while (!points.isEmpty()) {
                int[] point = points.poll();
                count += point[1];

                while (!points.isEmpty() && points.peek()[0] == point[0]) {
                    point = points.poll();
                    count += point[1];
                }

                max = Math.max(count, max);
            }

            writer.write(Integer.toString(max));

            writer.close();
            reader.close();
        }

    }
