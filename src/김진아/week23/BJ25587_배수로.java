package 김진아.week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ25587_배수로 {

    static int N;
    static int flood;
    static int[] drains;
    static int[] rains;
    static int[] roots;
    static int[] counts;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        drains = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) drains[i] = Integer.parseInt(input[i]);

        rains = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) rains[i] = Integer.parseInt(input[i]);

        roots = new int[N];
        for (int i = 0; i < N; i++) roots[i] = i;

        counts = new int[N];
        for (int i = 0; i < N; i++) counts[i] = 1;

        flood = 0;
        for (int i = 0; i < N; i++) {
            if (drains[i] < rains[i]) flood++;
        }

        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            if (input[0].equals("2")) builder.append(flood).append("\n");
            else union(Integer.parseInt(input[1]) - 1, Integer.parseInt(input[2]) - 1);
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void union(int city1, int city2) {
        int root1 = find(city1);
        int root2 = find(city2);
        if (root1 < root2) union(city1, root1, city2, root2);
        else if (root1 > root2) union(city2, root2, city1, root1);
    }

    static void union(int city1, int root1, int city2, int root2) {
        if (drains[root1] < rains[root1]) flood -= counts[root1];
        if (drains[root2] < rains[root2]) flood -= counts[root2];

        roots[root2] = root1;

        drains[root1] += drains[root2];
        rains[root1] += rains[root2];
        counts[root1] += counts[root2];

        if (drains[root1] < rains[root1]) flood += counts[root1];
    }

    static int find(int city) {
        if (roots[city] != city) roots[city] = find(roots[city]);
        return roots[city];
    }

}
