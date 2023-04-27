package 박지영.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9934_완전이진트리 {
    static int arr[];
    static class Node {
        int value, size;

        public Node(int value, int size) {
            this.value = value;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", size=" + size +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int size = (int) (Math.pow(2, K) - 1);
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(size / 2, size / 2));

        int curSize = size / 2;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.value < 0) break;
            if (curSize != cur.size) {
                curSize = cur.size;
                sb.append("\n");
            }
            sb.append(arr[cur.value]).append(" ");

            q.offer(new Node((cur.value - 1) - cur.size / 2, cur.size / 2));    // 왼쪽
            q.offer(new Node((cur.value + 1) + cur.size / 2, cur.size / 2));    // 오른쪽
        }

        System.out.println(sb);
    }

    static void binary(int size) {

    }
}