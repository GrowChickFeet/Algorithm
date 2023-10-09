package 김진아.week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ16395_파스칼의_삼각형 {

    static int[][] triangle;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        triangle = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            triangle[i][1] = 1;
            triangle[i][i] = 1;
        }

        writer.write(Integer.toString(pascal(n, k)));

        writer.close();
        reader.close();
    }

    static int pascal(int n, int k) {
        if (triangle[n][k] == 0) triangle[n][k] = pascal(n - 1, k - 1) + pascal(n - 1, k);
        return triangle[n][k];
    }

}
