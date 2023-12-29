package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1535_안녕 {

    final static int HP = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int[][] greetings = new int[N][2];

        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) greetings[i][0] = Integer.parseInt(input[i]);
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) greetings[i][1] = Integer.parseInt(input[i]);

        int[][] happy = new int[N][HP];
        for (int i = greetings[0][0]; i < HP; i++) happy[0][i] = greetings[0][1];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < greetings[i][0]; j++) happy[i][j] = happy[i - 1][j];
            for (int j = greetings[i][0]; j < HP; j++) happy[i][j] = Math.max(greetings[i][1] + happy[i - 1][j - greetings[i][0]], happy[i - 1][j]);
        }

        writer.write(Integer.toString(happy[N - 1][HP - 1]));

        writer.close();
        reader.close();
    }

}
