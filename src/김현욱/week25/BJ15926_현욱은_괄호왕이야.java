package 김현욱.week25;

import java.io.*;
import java.util.*;

public class BJ15926_현욱은_괄호왕이야 {
    static StringTokenizer st;

    static class Node implements Comparable<Node> {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        char[] maze = br.readLine().toCharArray();

        Stack<Integer> stack = new Stack<>();
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = maze[i];

            if (c == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    int popped = stack.pop();
                    if (!result.isEmpty() && result.get(result.size() - 1).end + 1 == popped) {
                        result.get(result.size() - 1).end = i;
                    } else {
                        result.add(new Node(popped, i));
                    }
                }
            }
        }
        Collections.sort(result);
        int max = 0;
        if (!result.isEmpty()) {
            int start = result.get(0).start;
            int end = result.get(0).end;
            for (int i = 1; i < result.size(); i++) {
                Node pop = result.get(i);
                if (start < pop.start && pop.end < end) continue;
                else if (end + 1 == pop.start) {
                    end = pop.end;
                } else {
                    max = Math.max(max, end - start + 1);
                    start = pop.start;
                    end = pop.end;
                }
            }
            max = Math.max(max, end - start + 1);
        }
        bw.write(Integer.toString(max));
        br.close();
        bw.close();
    }
}
