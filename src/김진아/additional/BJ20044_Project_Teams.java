package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ20044_Project_Teams {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        int[] levels = new int[n * 2];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < n * 2; i++) levels[i] = Integer.parseInt(input[i]);

        Arrays.sort(levels);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) min = Math.min(levels[i] + levels[n * 2 - 1 - i], min);

        writer.write(Integer.toString(min));

        writer.close();
        reader.close();
    }

}
