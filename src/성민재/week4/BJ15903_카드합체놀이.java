package 성민재.week4;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ15903_카드합체놀이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 카드 개수
        int m = Integer.parseInt(st.nextToken()); // 카드 합체가 몇번인지.

        PriorityQueue<Long> pq = new PriorityQueue();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            long first = pq.poll();
            long second = pq.poll();

            pq.add(first+second);
            pq.add(first+second);
        }
        long ans = 0;
        for (long num : pq){
            ans += num;
        }
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
    }

}

