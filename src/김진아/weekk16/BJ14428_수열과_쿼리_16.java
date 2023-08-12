import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    static int N;
    static int[] nums;
    static int[] min;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        N = Integer.parseInt(reader.readLine());

        nums = new int[N + 1];
        nums[0] = Integer.MAX_VALUE;
        String[] input = reader.readLine().split(" ");
        for (int i = 1; i <= N; i++) nums[i] = Integer.parseInt(input[i - 1]);

        min = new int[(int) Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)) + 1)];
        init(1, N, 1);

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            if (input[0].equals("1")) update(1, N, 1, Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            else builder.append(min(1, N, 1, Integer.parseInt(input[1]), Integer.parseInt(input[2]))).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void init(int start, int end, int index) {
        if (start == end) {
            min[index] = start;
            return;
        }

        int mid = (start + end) / 2;
        init(start, mid, index * 2);
        init(mid + 1, end, index * 2 + 1);

        if (nums[min[index * 2]] <= nums[min[index * 2 + 1]]) min[index] = min[index * 2];
        else min[index] = min[index * 2 + 1];
    }

    static void update(int start, int end, int index, int updateIndex, int value) {
        if (start > updateIndex || end < updateIndex) return;
        if (start == end) {
            min[index] = updateIndex;
            nums[updateIndex] = value;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, index * 2, updateIndex, value);
        update(mid + 1, end, index * 2 + 1, updateIndex, value);

        if (nums[min[index * 2]] <= nums[min[index * 2 + 1]]) min[index] = min[index * 2];
        else min[index] = min[index * 2 + 1];
    }

    static int min(int start, int end, int index, int left, int right) {
        if (start > right || end < left) return 0;
        if (start >= left && end <= right) return min[index];

        int mid = (start + end) / 2;
        int leftMin = min(start, mid, index * 2, left, right);
        int rightMin = min(mid + 1, end, index * 2 + 1, left, right);

        if (nums[leftMin] <= nums[rightMin]) return leftMin;
        else return rightMin;
    }

}
