import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    final static int MOD = 1000000007;

    static int[] nums;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) nums[i] = Integer.parseInt(reader.readLine());

        tree = new long[(int) Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)) + 1)];
        init(1, N, 1);

        for (int i = 0; i < M + K; i++) {
            input = reader.readLine().split(" ");
            if (input[0].equals("1")) update(1, N, 1, Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            else builder.append(multiple(1, N, 1, Integer.parseInt(input[1]), Integer.parseInt(input[2]))).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static long init(int start, int end, int index) {
        if (start == end) return tree[index] = nums[start];
        int mid = (start + end) / 2;
        return tree[index] = init(start, mid, index * 2) * init(mid + 1, end, index * 2 + 1) % MOD;
    }

    static long multiple(int start, int end, int index, int left, int right) {
        if (start > right || end < left) return 1;
        if (start >= left && end <= right) return tree[index];
        int mid = (start + end) / 2;
        return multiple(start, mid, index * 2, left, right) * multiple(mid + 1, end, index * 2 + 1, left, right) % MOD;
    }

    static void update(int start, int end, int index, int updateIndex, int value) {
        if (start > updateIndex || end < updateIndex) return;
        if (start == end) {
            tree[index] = value;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, index * 2, updateIndex, value);
        update(mid + 1, end, index * 2 + 1, updateIndex, value);

        tree[index] = tree[index * 2] * tree[index * 2 + 1] % MOD;
    }

}
