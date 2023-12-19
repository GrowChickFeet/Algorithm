package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ25187_고인물이_싫어요 {

    static int[] water;
    static int[] root;
    static int[] count;
    static int[] waterCount;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int Q = Integer.parseInt(input[2]);

        water = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) water[i] = Integer.parseInt(input[i]);

        root = new int[N];
        count = new int[N];
        waterCount = new int[N];
        for (int i = 0; i < N; i++) {
            root[i] = i;
            count[i] = 1;
            waterCount[i] = water[i];
        }

        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            union(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);
        }

        for (int i = 0; i < Q; i++) {
            int tank = root(Integer.parseInt(reader.readLine()) - 1);
            builder.append(waterCount[tank] > count[tank] - waterCount[tank] ? "1\n" : "0\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void union(int tank1, int tank2) {
        int root1 = root(tank1);
        int root2 = root(tank2);

        if (root1 < root2) {
            root[root2] = root1;
            count[root1] += count[root2];
            waterCount[root1] += waterCount[root2];
        } else if (root1 > root2) {
            root[root1] = root2;
            count[root2] += count[root1];
            waterCount[root2] += waterCount[root1];
        }
    }

    static int root(int tank) {
        if (root[tank] != tank) root[tank] = root(root[tank]);
        return root[tank];
    }

}
