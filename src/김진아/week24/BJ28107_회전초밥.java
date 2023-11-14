package 김진아.week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.PriorityQueue;

public class BJ28107_회전초밥 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        HashMap<Integer, PriorityQueue<Integer>> orders = new HashMap<>();
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            for (int j = 1; j <= k; j++) {
                int sushi = Integer.parseInt(input[j]);
                if (!orders.containsKey(sushi)) orders.put(sushi, new PriorityQueue<>());
                orders.get(sushi).offer(i);
            }
        }

        int[] count = new int[N];

        input = reader.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            int sushi = Integer.parseInt(input[i]);
            if (orders.containsKey(sushi) && !orders.get(sushi).isEmpty()) count[orders.get(sushi).poll()]++;
        }

        for (int i = 0; i < N; i++) builder.append(count[i]).append(" ");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
