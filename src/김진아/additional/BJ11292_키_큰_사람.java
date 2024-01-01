package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

class Height {

    String name;
    double height;

    public Height(String input) {
        String[] info = input.split(" ");
        name = info[0];
        height = Double.parseDouble(info[1]);
    }

}

public class BJ11292_키_큰_사람 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N;
        while ((N = Integer.parseInt(reader.readLine())) != 0) {
            Height[] heights = new Height[N];
            for (int i = 0; i < N; i++) heights[i] = new Height(reader.readLine());

            Arrays.sort(heights, (height1, height2) -> Double.compare(height2.height, height1.height));

            double tallest = heights[0].height;
            for (int i = 0; i < N; i++) {
                if (heights[i].height != tallest) break;
                builder.append(heights[i].name).append(" ");
            }
            builder.append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
