package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ16435_스네이크버드 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);

        int[] fruits = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) fruits[i] = Integer.parseInt(input[i]);

        Arrays.sort(fruits);

        for (int i = 0; i < N; i++) {
            if (fruits[i] > L) break;
            L++;
        }

        writer.write(Integer.toString(L));

        writer.close();
        reader.close();
    }

}
