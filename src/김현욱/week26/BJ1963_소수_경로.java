package 김현욱.week26;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1963_소수_경로 {
    public static final int MAX = 10000;
    public static boolean[] isPrime = new boolean[MAX];

    public static void makePrime() {
        Arrays.fill(isPrime, true);
        for (int i = 2; i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i + i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public static void makeNextPrime(Queue<Node> q, boolean[] visited, Node node) {
        int value = node.value;
        int count = node.count;

        for (int i = 4; i > 0; i--) {
            int g1 = (int) Math.pow(10, i);
            int g2 = (int) Math.pow(10, i - 1);
            int front = value / g1;
            int back = value % g2;

            for (int j = 0; j < 10; j++) {
                if (i == 4 && j == 0) continue;

                int nextValue = front * g1 + j * g2 + back;

                if (!visited[nextValue] && isPrime[nextValue]) {
                    q.offer(new Node(nextValue, count + 1));
                    visited[nextValue] = true;
                }
            }
        }
    }

    public static class Node {
        int value;
        int count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_case = Integer.parseInt(br.readLine());
        makePrime();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < test_case; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[MAX];
            Queue<Node> q = new ArrayDeque<>();
            int result = -1;

            visited[a] = true;
            q.offer(new Node(a, 0));

            while (!q.isEmpty()) {
                Node node = q.poll();

                if (node.value == b) {
                    result = node.count;
                    break;
                }

                makeNextPrime(q, visited, node);
            }

            sb.append(result == -1 ? "Impossible" : result).append('\n');
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
