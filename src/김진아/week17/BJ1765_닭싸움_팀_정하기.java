import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int n;
    static ArrayList<ArrayList<Integer>> friends;
    static ArrayList<ArrayList<Integer>> enemies;

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(reader.readLine());

        friends = new ArrayList<>();
        for (int i = 0; i < n; i++) friends.add(new ArrayList<>());
        enemies = new ArrayList<>();
        for (int i = 0; i < n; i++) enemies.add(new ArrayList<>());

        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            String[] input = reader.readLine().split(" ");
            int student1 = Integer.parseInt(input[1]) - 1;
            int student2 = Integer.parseInt(input[2]) - 1;
            if (input[0].equals("F")) {
                friends.get(student1).add(student2);
                friends.get(student2).add(student1);
            } else {
                enemies.get(student1).add(student2);
                enemies.get(student2).add(student1);
            }
        }

        writer.write(Integer.toString(count()));

        writer.close();
        reader.close();
    }

    static int count() {
        int count = 0;

        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            makeTeam(i);
            count++;
        }

        return count;
    }

    static void makeTeam(int student) {
        Queue<Integer> queue = new LinkedList<>();

        visited[student] = true;
        queue.offer(student);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : friends.get(now)) {
                if (visited[next]) continue;
                visited[next] = true;
                queue.offer(next);
            }

            for (int enemy : enemies.get(now)) {
                for (int next : enemies.get(enemy)) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

}
