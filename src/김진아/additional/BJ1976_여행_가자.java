package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1976_여행_가자 {

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int M = Integer.parseInt(reader.readLine());

        root = new int[N];
        for (int i = 0; i < N; i++) root[i] = i;
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = i + 1; j < N; j++) {
                if (input[j].equals("1")) union(i, j);
            }
        }

        String[] input = reader.readLine().split(" ");
        int[] travel = new int[M];
        for (int i = 0; i < M; i++) travel[i] = Integer.parseInt(input[i]) - 1;

        boolean possible = true;
        int travelRoot = find(travel[0]);
        for (int i = 1; i < M; i++) {
            if (find(travel[i]) == travelRoot) continue;
            possible = false;
            break;
        }

        writer.write(possible ? "YES" : "NO");

        writer.close();
        reader.close();
    }

    static void union(int city1, int city2) {
        int root1 = find(city1);
        int root2 = find(city2);

        if (root1 < root2) root[root2] = root1;
        else root[root1] = root2;
    }

    static int find(int city) {
        if (root[city] != city) root[city] = find(root[city]);
        return root[city];
    }

}
