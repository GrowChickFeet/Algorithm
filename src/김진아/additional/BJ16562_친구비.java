package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ16562_친구비 {

    static int[] root;
    static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        root = new int[N];
        for (int i = 0; i < N; i++) root[i] = i;

        price = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) price[i] = Integer.parseInt(input[i]);

        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            union(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);
        }

        boolean[] isFriend = new boolean[N];
        int sum = 0;

        for (int i = 0; i < N; i++) {
            int root = find(i);
            if (isFriend[root]) continue;
            sum += price[root];
            isFriend[root] = true;
        }

        writer.write(sum > k ? "Oh no" : Integer.toString(sum));

        writer.close();
        reader.close();
    }

    static void union(int student1, int student2) {
        int root1 = find(student1);
        int root2 = find(student2);

        if (root1 < root2) {
            root[root2] = root1;
            price[root1] = Math.min(price[root1], price[root2]);
        } else if (root1 > root2) {
            root[root1] = root2;
            price[root2] = Math.min(price[root1], price[root2]);
        }
    }

    static int find(int student) {
        if (root[student] != student) root[student] = find(root[student]);
        return root[student];
    }

}
