package 남현실.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1327_소트_게임 {
    static Set<String> visited = new HashSet<>();

    static class Node {
        String str;
        int cnt;

        public Node(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "str='" + str + '\'' +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        // get input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        // get string & deep copy for origin array
        temp = br.readLine().split(" ");
        String[] origin = new String[N];
        StringBuilder tempsb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            origin[i] = temp[i];
            tempsb.append(temp[i]);
        }

        String str = tempsb.toString();
        // init queue and visited
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(str, 0));
        visited.add(str);

        // reverse origin array and get expect result
        Arrays.sort(origin);
        tempsb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            tempsb.append(origin[i]);
        }
        String expect = tempsb.toString();

        Node cur;
        while (!q.isEmpty()) {
            cur = q.poll();

            if (expect.equals(cur.str)) { // return min count
                System.out.println(cur.cnt);
                return;
            }

            int ncnt = cur.cnt + 1;
            for (int i = 0; i <= N - M; i++) {
                // make str
                String nstr;
                String start, reverseMid, end;
                StringBuilder nsb = new StringBuilder();

                start = cur.str.substring(0, i);
                reverseMid = new StringBuilder(cur.str).reverse().substring(N - i - M, N - i);
                end = cur.str.substring(i + M, N);

                nsb.append(start)
                        .append(reverseMid)
                        .append(end);

                nstr = nsb.toString();

                if(visited.contains(nstr)) {
                    continue;
                }
                q.offer(new Node(nstr, ncnt));
                visited.add(nstr);
            }
        }
        System.out.println(-1); // return impossible
    }

}
