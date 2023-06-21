/*
https://www.acmicpc.net/problem/1181
 */
package 남현실.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BJ1181_단어_정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() != o2.length()) {
                return Integer.compare(o1.length(), o2.length());
            } else {
                return o1.compareTo(o2);
            }
        });

        String prev = "";
        for (String word : words) {
            if (prev.equals(word)) {
                continue;
            }
            System.out.println(word);
            prev = word;
        }
    }
}
