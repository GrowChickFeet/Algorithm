package 김진아.week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ11812_K진_트리 {

    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        K = Integer.parseInt(input[1]);
        int Q = Integer.parseInt(input[2]);

        for (int i = 0; i < Q; i++) {
            input = reader.readLine().split(" ");
            long node1 = Long.parseLong(input[0]);
            long node2 = Long.parseLong(input[1]);
            if (node1 < node2) builder.append(distance(node1, node2)).append("\n");
            else builder.append(distance(node2, node1)).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static long distance(long node1, long node2) {
        long distance = 0;

        long depth1 = depth(node1);
        long depth2 = depth(node2);

        if (depth1 < depth2) {
            distance += depth2 - depth1;
            node2 = ancestor(node2, distance);
        }

        while (node1 != node2) {
            distance += 2;
            node1 = ancestor(node1, 1);
            node2 = ancestor(node2, 1);
        }

        return distance;
    }

    static long depth(long node) {
        if (K == 1) return node - 1;
        if (K == 2) return (long) (Math.log(node) / Math.log(2));
        long last = 0;
        long depth = 0;
        while (node > last) last += (long) Math.pow(K, depth++);
        return depth - 1;
    }

    static long ancestor(long node, long relationship) {
        if (K == 1) return node - relationship;
        if (K == 2) return node / (long) Math.pow(2, relationship);
        long ancestor = node;
        for (int i = 0; i < relationship; i++) ancestor = (ancestor - 2) / K + 1;
        return ancestor;
    }

}
