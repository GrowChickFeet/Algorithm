package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;

public class BJ1302_베스트셀러 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, Integer> books = new HashMap<>();
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String book = reader.readLine();
            books.put(book, books.getOrDefault(book, 0) + 1);
        }

        String bestseller = "";
        int bestsellerCount = 0;
        for (String book : books.keySet()) {
            int count = books.get(book);
            if (count > bestsellerCount || (count == bestsellerCount && book.compareTo(bestseller) < 0)) {
                bestseller = book;
                bestsellerCount = count;
            }
        }

        writer.write(bestseller);

        writer.close();
        reader.close();
    }

}
