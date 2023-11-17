package 김현욱.week24;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ28107_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer>[] pq = new PriorityQueue[200001];
        for (int i = 0; i < pq.length; i++) {
            pq[i] = new PriorityQueue<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int value = Integer.parseInt(st.nextToken());
                pq[value].offer(i);
            }
        }
        int[] answer = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int food = Integer.parseInt(st.nextToken());
            if (!pq[food].isEmpty()) {
                int eat = pq[food].poll();
                answer[eat]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(answer[i]).append(' ');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
