package 김현욱.week24;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ1464_뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Character> dq = new ArrayDeque<>();
        char[] ch = br.readLine().toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (dq.isEmpty()) {
                dq.offerLast(ch[i]);
            } else {
                if (dq.peekLast() < ch[i]) {
                    dq.offerFirst(ch[i]);
                } else {
                    dq.offerLast(ch[i]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append(dq.pollLast());
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
