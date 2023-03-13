package 남현실.week3;

/*
https://www.acmicpc.net/problem/1922
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BJ1922_네트워크_연결 { // prim
    static class Vertex implements Comparable<Vertex> {
        int no, weight;

        public Vertex(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        // get input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // get graph
        LinkedList<Vertex>[] graph = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new LinkedList<>();
        }

        int from, to, weight;
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            from = Integer.parseInt(temp[0])-1;
            to = Integer.parseInt(temp[1])-1;
            weight = Integer.parseInt(temp[2]);

            graph[from].add(new Vertex(to, weight));
            graph[to].add(new Vertex(from, weight));
        }

        boolean[] visited = new boolean[N];
        int[] minWeight = new int[N];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        minWeight[0] = 0;
        pq.offer(new Vertex(0, 0));

        Vertex cur;
        int minSum= 0;
        int vCnt = 0;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            if(visited[cur.no]) {
                continue;
            }

            visited[cur.no] = true;
            minSum += cur.weight;

            if(++vCnt == N) {
                break;
            }

            for (Vertex v: graph[cur.no]) {
                if(visited[v.no]) {
                    continue;
                }
                if(minWeight[v.no] <= v.weight) {
                    continue;
                }
                minWeight[v.no] = v.weight;
                pq.offer(new Vertex(v.no, minWeight[v.no]));
            }
        }
        System.out.println(minSum);
        br.close();
    }
}
