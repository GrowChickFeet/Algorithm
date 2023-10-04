import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);

        HashMap<Integer, Integer> set = new HashMap<>();

        input = reader.readLine().split(" ");
        for (int i = 0; i < A; i++) {
            int num = Integer.parseInt(input[i]);
            set.put(num, set.getOrDefault(num, 0) + 1);
        }

        input = reader.readLine().split(" ");
        for (int i = 0; i < B; i++) {
            int num = Integer.parseInt(input[i]);
            set.put(num, set.getOrDefault(num, 0) + 1);
        }

        int count = 0;
        for (int num : set.keySet()) {
            if (set.get(num) == 1) count++;
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
