package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ2042_구간_합_구하기 {

    static long[] nums;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        nums = new long[N];
        for (int i = 0; i < N; i++) nums[i] = Long.parseLong(reader.readLine());

        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int length = (int) Math.pow(2, height + 1);

        tree = new long[length];
        init(0, N - 1, 1);

        for (int i = 0; i < M + K; i++) {
            input = reader.readLine().split(" ");
            if (input[0].equals("1")) update(0, N - 1, Integer.parseInt(input[1]) - 1, Long.parseLong(input[2]));
            else builder.append(sum(0, N - 1, 1, Integer.parseInt(input[1]) - 1, Integer.parseInt(input[2]) - 1)).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static long init(int start, int end, int node) {
        if (start == end) return tree[node] = nums[start];
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    static void update(int start, int end, int index, long value) {
        update(start, end, 1, index, value - nums[index]);
        nums[index] = value;
    }

    static void update(int start, int end, int node, int index, long difference) {
        if (index < start || index > end) return;
        tree[node] += difference;
        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, index, difference);
            update(mid + 1, end, node * 2 + 1, index, difference);
        }
    }

    static long sum(int start, int end, int node, int left, int right) {
        if (start > right || end < left) return 0;
        if (start >= left && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

}
