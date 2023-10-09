package 김진아.week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ20040_사이클_게임 {

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        root = new int[n];
        for (int i = 0; i < n; i++) root[i] = i;

        int turn = 0;
        for (int i = 0; i < m; i++) {
            input = reader.readLine().split(" ");
            int point1 = Integer.parseInt(input[0]);
            int point2 = Integer.parseInt(input[1]);

            if (union(point1, point2)) continue;

            turn = i + 1;
            break;
        }

        writer.write(Integer.toString(turn));

        writer.close();
        reader.close();
    }

    static boolean union(int point1, int point2) {
        int root1 = root(point1);
        int root2 = root(point2);

        if (root1 == root2) return false;

        if (root1 < root2) root[root2] = root1;
        else root[root1] = root2;

        return true;
    }

    static int root(int point) {
        if (root[point] != point) root[point] = root(root[point]);
        return root[point];
    }

}
