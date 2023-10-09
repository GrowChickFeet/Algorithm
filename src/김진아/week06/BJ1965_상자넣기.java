package 김진아.week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1965_상자넣기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        int[] boxes = new int[n];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) boxes[i] = Integer.parseInt(input[i]);

        int[] count = new int[n];
        for (int i = 0; i < n; i++) count[i] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (boxes[j] < boxes[i]) count[i] = Math.max(count[j] + 1, count[i]);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) max = Math.max(count[i], max);

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

}
