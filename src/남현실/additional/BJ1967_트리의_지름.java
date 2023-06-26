package 남현실.additional;

/*
https://www.acmicpc.net/problem/1967
 */

/*
트리의 지름 알고리즘
아무 노드에서 가장 먼 노드를 찾고
그 노드에서 가장 먼 노드를 찾으면 지름
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ1967_트리의_지름 {
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int maxSum;
    static int tgNum;

    static class Node {
        int num;
        int w;

        public Node(int num, int w) {
            this.num = num;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", w=" + w +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] temp;
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            temp = br.readLine().split(" ");
            int p = Integer.parseInt(temp[0]) - 1;
            int c = Integer.parseInt(temp[1]) - 1;
            int w = Integer.parseInt(temp[2]);
            graph[p].add(new Node(c, w));
            graph[c].add(new Node(p, w));
        }

        visited = new boolean[N];
        maxSum = 0;
        dfs(0, 0);

        visited = new boolean[N];
        maxSum = 0;
        dfs(tgNum, 0);
        System.out.println(maxSum);
    }

    static void dfs(int num, int sum) {
        if (maxSum < sum) {
            maxSum = sum;
            tgNum = num;
        }
        visited[num] = true;

        for (Node next : graph[num]) {
            if (visited[next.num]) {
                continue;
            }
            dfs(next.num, sum + next.w);
        }
    }
}
