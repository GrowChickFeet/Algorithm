package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ1167_트리의_지름 {

    static ArrayList<ArrayList<int[]>> tree;

    static int max;
    static int point;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        tree = new ArrayList<>();
        for (int i = 0; i < N; i++) tree.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            int index = 0;
            int node = Integer.parseInt(input[index++]) - 1;
            while (!input[index].equals("-1")) tree.get(node).add(new int[] { Integer.parseInt(input[index++]) - 1, Integer.parseInt(input[index++]) });
        }

        max = 0;

        boolean[] visited = new boolean[N];
        visited[0] = true;
        max(visited, 0, 0);

        visited = new boolean[N];
        visited[point] = true;
        max(visited, point, 0);

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

    static void max(boolean[] visited, int now, int length) {
        if (length > max) {
            max = length;
            point = now;
        }

        for (int[] next : tree.get(now)) {
            if (visited[next[0]]) continue;
            visited[next[0]] = true;
            max(visited, next[0], length + next[1]);
            visited[next[0]] = false;
        }
    }

}
