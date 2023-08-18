import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int n;
    static ArrayList<ArrayList<Integer>> graph;

    static StringBuilder builder;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int caseNumber = 1;

        while (n != 0 || m != 0) {
            graph = new ArrayList<>();
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                input = reader.readLine().split(" ");
                int node1 = Integer.parseInt(input[0]) - 1;
                int node2 = Integer.parseInt(input[1]) - 1;

                graph.get(node1).add(node2);
                if (node1 != node2) graph.get(node2).add(node1);
            }

            builder.append("Case ").append(caseNumber++).append(": ");
            countTree();
            builder.append("\n");

            input = reader.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void countTree() {
        int count = 0;

        boolean[] visitedNode = new boolean[n];
        boolean[][] visitedEdge = new boolean[n][n];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (visitedNode[i]) continue;

            visitedNode[i] = true;
            queue.offer(i);

            boolean isTree = true;

            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (int next : graph.get(now)) {
                    if (visitedNode[next]) {
                        if (!visitedEdge[now][next]) isTree = false;
                        continue;
                    }

                    visitedNode[next] = true;
                    visitedEdge[now][next] = true;
                    visitedEdge[next][now] = true;
                    queue.offer(next);
                }
            }

            if (isTree) count++;
        }

        if (count == 0) builder.append("No trees.");
        else if (count == 1) builder.append("There is one tree.");
        else builder.append("A forest of ").append(count).append(" trees.");
    }

}
