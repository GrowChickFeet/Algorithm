package 김진아.week09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.PriorityQueue;

public class BJ2211_네트워크_복구 {

    static int N;
    static int[][] graph;
    static StringBuilder builder;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        graph = new int[N][N];
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");

            int computer1 = Integer.parseInt(input[0]) - 1;
            int computer2 = Integer.parseInt(input[1]) - 1;
            int cost = Integer.parseInt(input[2]);

            graph[computer1][computer2] = cost;
            graph[computer2][computer1] = cost;
        }

        builder = new StringBuilder();
        recover();

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void recover() {
        int[] parent = new int[N];
        int[] distance = new int[N];
        for (int i = 0; i < N; i++) distance[i] = Integer.MAX_VALUE;

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        distance[0] = 0;
        queue.offer(new int[] { 0, 0 });

        while (!queue.isEmpty()) {
            int[] edge = queue.poll();

            for (int i = 0; i < N; i++) {
                if (graph[edge[0]][i] == 0) continue;

                int now = distance[edge[0]] + graph[edge[0]][i];
                if (now < distance[i]) {
                    distance[i] = now;
                    parent[i] = edge[0];
                    queue.offer(new int[] { i, distance[i] });
                }
            }
        }

        builder.append(N - 1).append("\n");
        for (int i = 1; i < N; i++) builder.append(i + 1).append(" ").append(parent[i] + 1).append("\n");
    }

}
