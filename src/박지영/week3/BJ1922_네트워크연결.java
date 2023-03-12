package 박지영.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
프림 알고리즘 사용
모든 컴퓨터를 연결하는데 필요한 최소비용을 구해야하기 때문에
모든 지점을 방문하는데 가장 작은 비용을 구하는 프림 알고리즘을 사용하였음
 */
public class BJ1922_네트워크연결 {
    static class Node implements Comparable<Node>{      // 우선순위큐 사용
        int vertex;
        int cost;
        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", cost=" + cost +
                    '}';
        }
    }
    static int N, M;
    static int[] minEdge;
    static boolean[] visited;
    static ArrayList<Node>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        minEdge = new int[N+1];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        visited = new boolean[N+1];
        adjList = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, cost));
            adjList[to].add(new Node(from, cost));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        minEdge[1] = 0;

        int result = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.vertex]) continue;
            result += current.cost;
            visited[current.vertex] = true;
            for(Node temp : adjList[current.vertex]) {
                if (!visited[temp.vertex] && minEdge[temp.vertex] >= temp.cost) {
                    minEdge[temp.vertex] = temp.cost;
                    pq.offer(new Node(temp.vertex, temp.cost));
                }
            }
        }
        System.out.println(result);

    }
}
