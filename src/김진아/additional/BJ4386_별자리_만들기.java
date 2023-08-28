import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

class Edge {

    int star1;
    int star2;
    double distance;

    public Edge(int star1, int star2, double distance) {
        this.star1 = star1;
        this.star2 = star2;
        this.distance = distance;
    }

}

public class Main {

    static int n;
    static float[][] stars;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(reader.readLine());

        stars = new float[n][2];
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            stars[i][0] = Float.parseFloat(input[0]);
            stars[i][1] = Float.parseFloat(input[1]);
        }

        writer.write(Double.toString(min()));

        writer.close();
        reader.close();
    }

    static double min() {
        root = new int[n];
        for (int i = 0; i < n; i++) root[i] = i;

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) edges.add(new Edge(i, j, Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2))));
        }

        Collections.sort(edges, (edge1, edge2) -> {
            if (edge1.distance >= edge2.distance) return 1;
            return -1;
        });

        double min = 0;
        for (Edge edge : edges) {
            if (root(edge.star1) == root(edge.star2)) continue;
            union(edge.star1, edge.star2);
            min += edge.distance;
        }
        return min;
    }

    static void union(int star1, int star2) {
        int root1 = root(star1);
        int root2 = root(star2);

        if (root1 < root2) root[root2] = root1;
        else root[root1] = root2;
    }

    static int root(int star) {
        if (root[star] != star) root[star] = root(root[star]);
        return root[star];
    }

}
