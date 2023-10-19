package 김진아.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BJ22865_가장_먼_곳 {

    static int N;
    static ArrayList<ArrayList<int[]>> edges;

    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        edges = new ArrayList<>();
        for (int i = 0; i < N; i++) edges.add(new ArrayList<>());

        String[] input = reader.readLine().split(" ");
        int[] friends = new int[3];
        for (int i = 0; i < 3; i++) friends[i] = Integer.parseInt(input[i]) - 1;

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int node1 = Integer.parseInt(input[0]) - 1;
            int node2 = Integer.parseInt(input[1]) - 1;
            int length = Integer.parseInt(input[2]);

            edges.get(node1).add(new int[] { node2, length });
            edges.get(node2).add(new int[] { node1, length });
        }

        distance = new int[3][N];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < N; j++) distance[i][j] = Integer.MAX_VALUE;
        }

        int index = 0;
        for (int friend : friends) getDistance(friend, index++);

        int num = -1;
        int max = 0;
        for (int i = 0; i < N; i++) {
            int min = Math.min(distance[0][i], Math.min(distance[1][i], distance[2][i]));
            if (min > max) {
                num = i + 1;
                max = min;
            }
        }

        writer.write(Integer.toString(num));

        writer.close();
        reader.close();
    }

    static void getDistance(int friend, int index) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((edge1, edge2) -> edge1[1] - edge2[1]);

        distance[index][friend] = 0;
        queue.offer(new int[] { friend, 0 });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int[] next : edges.get(now[0])) {
                if (distance[index][now[0]] + next[1] < distance[index][next[0]]) {
                    distance[index][next[0]] = distance[index][now[0]] + next[1];
                    queue.offer(new int[] { next[0], distance[index][next[0]] });
                }
            }
        }
    }

}
