package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;

public class BJ11652_카드 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<Long, Integer> cardCount = new HashMap<>();

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            long card = Long.parseLong(reader.readLine());
            cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
        }

        writer.write(Long.toString(cardCount.entrySet().stream().min((card1, card2) -> {
            int count1 = card1.getValue();
            int count2 = card2.getValue();
            if (count1 == count2) return card1.getKey().compareTo(card2.getKey());
            return count2 - count1;
        }).get().getKey()));

        writer.close();
        reader.close();
    }

}
