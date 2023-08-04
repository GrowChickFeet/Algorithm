import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);

        input = reader.readLine().split(" ");

        HashMap<Integer, int[]> counts = new HashMap<>();
        int index = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(input[i]);
            if (!counts.containsKey(num)) counts.put(num, new int[] { 1, index++ });
            else counts.get(num)[0]++;
        }

        ArrayList<Integer> nums = new ArrayList<>(counts.keySet());
        Collections.sort(nums, (o1, o2) -> {
            int[] count1 = counts.get(o1);
            int[] count2 = counts.get(o2);
            if (count1[0] == count2[0]) return count1[1] - count2[1];
            return count2[0] - count1[0];
        });

        for (int num : nums) {
            int count = counts.get(num)[0];
            for (int i = 0; i < count; i++) builder.append(num).append(" ");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
