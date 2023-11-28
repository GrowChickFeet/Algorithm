package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BJ2644_촌수계산 {

    static int n;
    static int[] parents;
    static ArrayList<ArrayList<Integer>> children;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(reader.readLine());
        parents = new int[n + 1];
        children = new ArrayList<>();
        for (int i = 0; i <= n; i++) children.add(new ArrayList<>());

        String[] input = reader.readLine().split(" ");
        int person1 = Integer.parseInt(input[0]);
        int person2 = Integer.parseInt(input[1]);

        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            input = reader.readLine().split(" ");
            int parent = Integer.parseInt(input[0]);
            int child = Integer.parseInt(input[1]);
            parents[child] = parent;
            children.get(parent).add(child);
        }

        writer.write(Integer.toString(relation(person1, person2)));

        writer.close();
        reader.close();
    }

    static int relation(int person1, int person2) {
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> queue = new LinkedList<>();

        visited[person1] = true;
        queue.offer(new int[] { person1, 0 });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (parents[now[0]] == person2) return now[1] + 1;
            if (!visited[parents[now[0]]]) {
                visited[parents[now[0]]] = true;
                queue.offer(new int[] { parents[now[0]], now[1] + 1 });
            }

            for (int child : children.get(now[0])) {
                if (child == person2) return now[1] + 1;
                if (!visited[child]) {
                    visited[child] = true;
                    queue.offer(new int[] { child, now[1] + 1 });
                }
            }
        }

        return -1;
    }

}
