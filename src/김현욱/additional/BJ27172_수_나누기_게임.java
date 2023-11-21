package 김현욱.additional;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ27172_수_나누기_게임 {
    static final int MAX = 1_000_000;

    static class Node implements Comparable<Node> {
        int num, index;

        public Node(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.num, this.num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Node[] arr = new Node[n];
        int[] result = new int[n];
        int[] indexes = new int[MAX + 1];
        for (int i = 0; i <= MAX; i++) {
            indexes[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            arr[i] = new Node(value, i);
            indexes[value] = i;
        }

        //내림차순으로 정렬
        Arrays.sort(arr);
        boolean[] check = new boolean[MAX + 1];
        for (Node node : arr) {
            int num = node.num;
            int index = node.index;

            check[num] = true;
            for (int i = num * 2; i <= MAX; i += num) {
                if (indexes[i] != -1 && check[i]) {
                    result[index]++;
                    result[indexes[i]]--;
                }
                check[i] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]).append(' ');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
