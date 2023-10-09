package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BJ20920_영단어_암기는_괴로워 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        HashMap<String, Integer> words = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = reader.readLine();
            if (word.length() >= M) words.put(word, words.getOrDefault(word, 0) + 1);
        }

        ArrayList<String> vocabulary = new ArrayList<>();
        for (String word : words.keySet()) vocabulary.add(word);

        Collections.sort(vocabulary, (word1, word2) -> {
            int count1 = words.get(word1);
            int count2 = words.get(word2);
            if (count1 != count2) return count2 - count1;

            int length1 = word1.length();
            int length2 = word2.length();
            if (length1 != length2) return length2 - length1;

            return word1.compareTo(word2);
        });

        for (String word : vocabulary) builder.append(word).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
