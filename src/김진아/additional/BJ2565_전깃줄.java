import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int[][] wires = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            wires[i][0] = Integer.parseInt(input[0]);
            wires[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(wires, (wire1, wire2) -> wire1[0] - wire2[0]);

        int max = 1;
        int[] maxWires = new int[N];
        maxWires[0] = wires[0][1];

        for (int i = 1; i < N; i++) {
            if (wires[i][1] > maxWires[max - 1]) {
                maxWires[max++] = wires[i][1];
                continue;
            }

            int index = 0;
            int start = 0;
            int end = max;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (wires[i][1] > maxWires[mid]) start = mid + 1;
                else {
                    index = mid;
                    end = mid - 1;
                }
            }

            maxWires[index] = wires[i][1];
        }

        writer.write(Integer.toString(N - max));

        writer.close();
        reader.close();
    }

}
