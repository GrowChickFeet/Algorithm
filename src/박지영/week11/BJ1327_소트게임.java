package 박지영.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1327_소트게임 {
    static class Number {
        String s;
        int count;

        public Number(String s, int count) {
            this.s = s;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "s='" + s + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

    static int N, K;
    static String check = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String s = "";
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            s += st.nextToken();
            check += (i+1);
        }
        System.out.print(bfs(s));
    }

    static int bfs(String s) {
        Queue<Number> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();      // ArrayList보다 빠름!
        q.offer(new Number(s, 0));

        while(!q.isEmpty()) {
            Number cur = q.poll();

            if (cur.s.equals(check)) {
                return cur.count;
            }
            if (!set.contains(cur.s)) {
                set.add(cur.s);
                for (int i = 0; i < N - K + 1; i++) {
                    String reverseStr = reverse(cur.s, i);
                    q.offer(new Number(reverseStr, cur.count + 1));
                }
            }
        }

        /*
        어째서 이거는 시간초과가 나는거지..
        while(!q.isEmpty()) {
            Number cur = q.poll();
            set.add(cur.s);


            for (int i = 0; i < N-K+1; i++) {
                String reverseStr = reverse(cur.s, i);
                if (reverseStr.equals(check)) {
                    return cur.count+1;
                }
                if (!set.contains(reverseStr)) q.offer(new Number(reverseStr, cur.count+1));
            }
        }
        */

        return -1;
    }


    static String reverse(String s, int start) {

        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, start));

        String str = s.substring(start, start+K);
        for (int i = K-1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        sb.append(s.substring(start+K));


        return sb.toString();
    }

}

