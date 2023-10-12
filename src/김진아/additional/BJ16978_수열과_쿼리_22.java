package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

public class BJ16978_수열과_쿼리_22 {
    
    static long[] nums;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        
        int N = Integer.parseInt(reader.readLine());
        nums = new long[N];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) nums[i] = Long.parseLong(input[i]);
        
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int length = (int) Math.pow(2, height + 1);
        tree = new long[length];
        init(0, N - 1, 1);
        
        ArrayList<int[]> updateQueries = new ArrayList<>();
        ArrayList<int[]> sumQueries = new ArrayList<>();
        
        int K = Integer.parseInt(reader.readLine());
        int index = 0;
        for (int i = 0; i < K; i++) {
            input = reader.readLine().split(" ");
            if (input[0].equals("1")) updateQueries.add(new int[] { Integer.parseInt(input[1]) - 1, Integer.parseInt(input[2]) });
            else sumQueries.add(new int[] { index++, Integer.parseInt(input[1]), Integer.parseInt(input[2]) - 1, Integer.parseInt(input[3]) - 1 });
        }
        
        Collections.sort(sumQueries, (sumQuery1, sumQuery2) -> sumQuery1[1] - sumQuery2[1]);
        
        long[] answers = new long[sumQueries.size()];
        
        int now = 0;
        for (int[] sumQuery : sumQueries) {
            while (sumQuery[1] > now) {
                int[] updateQuery = updateQueries.get(now++);
                update(0, N - 1, updateQuery[0], updateQuery[1]);
            }
            answers[sumQuery[0]] = sum(0, N - 1, 1, sumQuery[2], sumQuery[3]);
        }
        
        for (long answer : answers) builder.append(answer).append("\n");
        
        writer.write(builder.toString());

        writer.close();
        reader.close();
    }
    
    static long init(int start, int end, int node) {
        if (start == end) return tree[node] = nums[start];
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }
    
    static long sum(int start, int end, int node, int left, int right) {
        if (start > right || end < left) return 0;
        if (start >= left && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }
    
    static void update(int start, int end, int index, long value) {
        update(start, end, 1, index, value - nums[index]);
        nums[index] = value;
    }
    
    static void update(int start, int end, int node, int index, long difference) {
        if (index < start || index > end) return;
        tree[node] += difference;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, difference);
        update(mid + 1, end, node * 2 + 1, index, difference);
    }

}
