import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int N;
    static ArrayList<int[]> network;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        network = new ArrayList<>();

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) {
            String[] input = reader.readLine().split(" ");
            int computer1 = Integer.parseInt(input[0]);
            int computer2 = Integer.parseInt(input[1]);
            int price = Integer.parseInt(input[2]);
            network.add(new int[] { computer1, computer2, price });
        }

        writer.write(Integer.toString(min()));

        writer.close();
        reader.close();
    }

    static int min() {
        int min = 0;

        root = new int[N + 1];
        for (int i = 0; i < N; i++) root[i] = i;

        Collections.sort(network, (o1, o2) -> o1[2] - o2[2]);

        for (int[] edge : network) {
            if (find(edge[0]) == find(edge[1])) continue;
            union(edge[0], edge[1]);
            min += edge[2];
        }

        return min;
    }

    static int find(int computer) {
        if (root[computer] != computer) root[computer] = find(root[computer]);
        return root[computer];
    }

    static void union(int computer1, int computer2) {
        int root1 = find(computer1);
        int root2 = find(computer2);
        if (root1 < root2) root[root2] = root1;
        else root[root1] = root2;
    }

}
