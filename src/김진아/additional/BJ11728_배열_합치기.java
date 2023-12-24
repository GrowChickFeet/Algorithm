package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ11728_배열_합치기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[] result = new int[N + M];

        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) result[i] = Integer.parseInt(input[i]);

        input = reader.readLine().split(" ");
        for (int i = 0; i < M; i++) result[i + N] = Integer.parseInt(input[i]);

        Arrays.sort(result);

        for (int i = 0; i < N + M; i++) builder.append(result[i]).append(" ");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
