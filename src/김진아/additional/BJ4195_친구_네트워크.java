package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

public class BJ4195_친구_네트워크 {

    static HashMap<String, Integer> key;
    static ArrayList<Integer> root;
    static ArrayList<Integer> count;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int totalTestCase = Integer.parseInt(reader.readLine());
        for (int testCase = 0; testCase < totalTestCase; testCase++) {
            key = new HashMap<>();
            root = new ArrayList<>();
            count = new ArrayList<>();

            int F = Integer.parseInt(reader.readLine());
            int index = 0;

            for (int i = 0; i < F; i++) {
                String[] friends = reader.readLine().split(" ");

                for (String friend : friends) {
                    if (!key.containsKey(friend)) {
                        key.put(friend, index);
                        root.add(index++);
                        count.add(1);
                    }
                }

                builder.append(union(friends)).append("\n");
            }
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int union(String[] friends) {
        if (friends[0].compareTo(friends[1]) > 0) return union(friends[0], friends[1]);
        return union(friends[1], friends[0]);
    }

    static int union(String friend1, String friend2) {
        int key1 = key.get(friend1);
        int key2 = key.get(friend2);

        int root1 = find(key1);
        int root2 = find(key2);

        if (root1 != root2) {
            root.set(root2, root1);
            count.set(root1, count.get(root1) + count.get(root2));
        }

        return count.get(root1);
    }

    static int find(int key) {
        int parent = root.get(key);
        if (parent != key) root.set(key, find(parent));
        return root.get(key);
    }

}
