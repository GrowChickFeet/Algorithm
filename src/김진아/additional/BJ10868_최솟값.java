package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ10868_최솟값 {

    static int[] nums;
    static int[] minTree;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(reader.readLine());

        int height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int length = (int) (Math.pow(2, height)) - 1;

        minTree = new int[length + 1];
        init(0, N - 1, 1);

        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int start = Integer.parseInt(input[0]) - 1;
            int end = Integer.parseInt(input[1]) - 1;
            builder.append(min(0, N - 1, 1, start, end)).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int init(int start, int end, int index) {
        if (start == end) return minTree[index] = nums[start];
        int mid = (start + end) / 2;
        return minTree[index] = Math.min(init(start, mid, index * 2), init(mid + 1, end, index * 2 + 1));
    }

    static int min(int start, int end, int index, int inputStart, int inputEnd) {
        if (start > inputEnd || end < inputStart) return Integer.MAX_VALUE;
        if (start >= inputStart && end <= inputEnd) return minTree[index];
        int mid = (start + end) / 2;
        return Math.min(min(start, mid, index * 2, inputStart, inputEnd), min(mid + 1, end, index * 2 + 1, inputStart, inputEnd));
    }

}
