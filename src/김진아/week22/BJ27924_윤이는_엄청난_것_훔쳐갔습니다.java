package 김진아.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {

    ArrayList<Integer> next;

    public Node() {
        next = new ArrayList<>();
    }

    public boolean isLeaf() {
        return next.size() == 1;
    }

}

public class BJ27924_윤이는_엄청난_것_훔쳐갔습니다 {

    static int N;
    static Node[] tree;
    static int[] start;
    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        tree = new Node[N];
        for (int i = 0; i < N; i++) tree[i] = new Node();
        for (int i = 0; i < N - 1; i++) {
            String[] input = reader.readLine().split(" ");
            int node1 = Integer.parseInt(input[0]) - 1;
            int node2 = Integer.parseInt(input[1]) - 1;
            tree[node1].next.add(node2);
            tree[node2].next.add(node1);
        }

        start = new int[3];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < 3; i++) start[i] = Integer.parseInt(input[i]) - 1;

        writer.write(escape() ? "YES" : "NO");

        writer.close();
        reader.close();
    }

    static boolean escape() {
        distance = new int[3][N];
        for (int i = 0; i < 3; i++) getDistance(i);
        for (int i = 0; i < N; i++) {
            if (tree[i].isLeaf() && distance[0][i] < distance[1][i] && distance[0][i] < distance[2][i]) return true;
        }
        return false;
    }

    static void getDistance(int startIndex) {
        for (int i = 0; i < N; i++) distance[startIndex][i] = -1;

        Queue<Integer> queue = new LinkedList<>();

        distance[startIndex][start[startIndex]] = 0;
        queue.offer(start[startIndex]);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : tree[now].next) {
                if (distance[startIndex][next] != -1) continue;
                distance[startIndex][next] = distance[startIndex][now] + 1;
                queue.offer(next);
            }
        }
    }

}
