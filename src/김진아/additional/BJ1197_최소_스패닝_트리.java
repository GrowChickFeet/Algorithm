package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ1197_최소_스패닝_트리 {

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);

        int[][] edges = new int[E][3];
        for (int i = 0; i < E; i++) {
            input = reader.readLine().split(" ");
            edges[i][0] = Integer.parseInt(input[0]) - 1;
            edges[i][1] = Integer.parseInt(input[1]) - 1;
            edges[i][2] = Integer.parseInt(input[2]);
        }

        Arrays.sort(edges, (edge1, edge2) -> edge1[2] - edge2[2]);

        root = new int[V];
        for (int i = 0; i < V; i++) root[i] = i;

        int sum = 0;
        for (int[] edge : edges) {
            if (union(edge[0], edge[1])) sum += edge[2];
        }

        writer.write(Integer.toString(sum));

        writer.close();
        reader.close();
    }

    static boolean union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) return false;

        if (root1 < root2) root[root2] = root1;
        else root[root1] = root2;

        return true;
    }

    static int find(int node) {
        if (root[node] != node) root[node] = find(root[node]);
        return root[node];
    }

}
