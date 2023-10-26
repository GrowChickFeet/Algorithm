package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.PriorityQueue;

public class BJ14235_크리스마스_선물 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        PriorityQueue<Integer> gifts = new PriorityQueue<>((gift1, gift2) -> gift2 - gift1);

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");

            if (input[0].equals("0")) builder.append(gifts.isEmpty() ? "-1" : gifts.poll()).append("\n");
            else {
                int count = Integer.parseInt(input[0]);
                for (int j = 1; j <= count; j++) gifts.offer(Integer.parseInt(input[j]));
            }
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
