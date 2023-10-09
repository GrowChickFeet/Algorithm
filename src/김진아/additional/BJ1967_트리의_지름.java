package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ1967_트리의_지름 {

    static ArrayList<ArrayList<int[]>> graph;
    static int diameter;
    static int point;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            String[] input = reader.readLine().split(" ");
            int node1 = Integer.parseInt(input[0]) - 1;
            int node2 = Integer.parseInt(input[1]) - 1;
            int distance = Integer.parseInt(input[2]);

            graph.get(node1).add(new int[] { node2, distance });
            graph.get(node2).add(new int[] { node1, distance });
        }

        diameter = 0;
        point = 0;
        for (int i = 0; i < 2; i++) {
            boolean[] visited = new boolean[n];
            visited[point] = true;
            getDiameter(point, 0, visited);
        }

        writer.write(Integer.toString(diameter));

        writer.close();
        reader.close();
    }

    static void getDiameter(int node, int distance, boolean[] visited) {
        if (distance > diameter) {
            diameter = distance;
            point = node;
        }

        for (int[] next : graph.get(node)) {
            if (visited[next[0]]) continue;
            visited[next[0]] = true;
            getDiameter(next[0], distance + next[1], visited);
            visited[next[0]] = false;
        }
    }

}
