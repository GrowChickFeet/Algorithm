package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayDeque;

public class BJ2346_풍선_터뜨리기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());

        ArrayDeque<int[]> balloons = new ArrayDeque<>();
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) balloons.add(new int[] { i + 1, Integer.parseInt(input[i]) });

        int move = 0;
        while (!balloons.isEmpty()) {
            if (move < 0) {
                for (int i = 1; i < -move; i++) balloons.offerFirst(balloons.pollLast());
                int[] balloon = balloons.pollLast();
                builder.append(balloon[0]).append(" ");
                move = balloon[1];
            } else {
                for (int i = 1; i < move; i++) balloons.offerLast(balloons.pollFirst());
                int[] balloon = balloons.pollFirst();
                builder.append(balloon[0]).append(" ");
                move = balloon[1];
            }
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
