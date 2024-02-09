package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayDeque;

public class BJ18115_카드_놓기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        ArrayDeque<Integer> result = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) result.offerLast(i);

        String[] input = reader.readLine().split(" ");
        ArrayDeque<Integer> init = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            int skill = Integer.parseInt(input[i]);
            if (skill == 1) init.offerFirst(result.pollFirst());
            else if (skill == 3) init.offerLast(result.pollFirst());
            else {
                int top = init.pollFirst();
                init.offerFirst(result.pollFirst());
                init.offerFirst(top);
            }
        }

        init.forEach(card -> builder.append(card).append(" "));

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
