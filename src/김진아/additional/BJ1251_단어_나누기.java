package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ1251_단어_나누기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = reader.readLine();
        int length = word.length();

        ArrayList<String> newWords = new ArrayList<>();
        for (int i = 1; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                newWords.add(new StringBuilder().append(new StringBuilder(word.substring(0, i)).reverse())
                                            .append(new StringBuilder(word.substring(i, j)).reverse())
                                            .append(new StringBuilder(word.substring(j)).reverse())
                                            .toString());
            }
        }

        newWords.sort((newWord1, newWord2) -> newWord1.compareTo(newWord2));

        writer.write(newWords.get(0));

        writer.close();
        reader.close();
    }

}
