package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1717_집합의_표현 {

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        root = new int[n + 1];
        for (int i = 0; i <= n; i++) root[i] = i;

        for (int i = 0; i < m; i++) {
            input = reader.readLine().split(" ");
            if (input[0].equals("0")) union(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            else {
                if (root(Integer.parseInt(input[1])) == root(Integer.parseInt(input[2]))) builder.append("YES\n");
                else builder.append("NO\n");
            }
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void union(int element1, int element2) {
        int root1 = root(element1);
        int root2 = root(element2);

        if (root1 <= root2) root[root2] = root1;
        else root[root1] = root2;
    }

    static int root(int element) {
        if (root[element] != element) root[element] = root(root[element]);
        return root[element];
    }

}
