package 김진아.week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ11909_배열_탈출 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < n; j++) arr[i][j] = Integer.parseInt(input[j]);
        }

        int[][] price = new int[n][n];
        for (int i = 1; i < n; i++) {
            price[0][i] = price[0][i - 1] + (arr[0][i] < arr[0][i - 1] ? 0 : arr[0][i] - arr[0][i - 1] + 1);
            price[i][0] = price[i - 1][0] + (arr[i][0] < arr[i - 1][0] ? 0 : arr[i][0] - arr[i - 1][0] + 1);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                price[i][j] = Math.min(price[i][j - 1] + (arr[i][j] < arr[i][j - 1] ? 0 : arr[i][j] - arr[i][j - 1] + 1),
                                        price[i - 1][j] + (arr[i][j] < arr[i - 1][j] ? 0 : arr[i][j] - arr[i - 1][j] + 1));
            }
        }

        writer.write(Integer.toString(price[n - 1][n - 1]));

        writer.close();
        reader.close();
    }

}
