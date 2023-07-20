package 박지영.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  문자열 길이 순서대로 정렬
 *  앞에서부터 뒤를 비교 -> 접두사인 단어가 있다면 짧은 단어를 지움 (count--)
 */
public class BJ1141_접두사 {
    static int N, count;
    static String[] words;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = count = Integer.parseInt(br.readLine());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        // 단어의 길이로 정렬
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                if (isPrefix(words[i], words[j])) { // w1가 접두사 일사
                    count--;
                    break;
                }
            }
        }

        System.out.print(count);
    }

    static boolean isPrefix(String w1, String w2) {     // 길이: w1 < w2
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) return false;     // 접두어 관계가 아님
        }
        return true;        // 접두어 관계임
    }
}
