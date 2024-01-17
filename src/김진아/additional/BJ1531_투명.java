package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1531_투명 {

    final static int LENGTH = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] picture = new int[LENGTH + 1][LENGTH + 1];

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            int x1 = Integer.parseInt(input[0]) - 1;
            int y1 = Integer.parseInt(input[1]) - 1;
            int x2 = Integer.parseInt(input[2]) - 1;
            int y2 = Integer.parseInt(input[3]) - 1;

            picture[x1][y1]++;
            picture[x1][y2 + 1]--;
            picture[x2 + 1][y1]--;
            picture[x2 + 1][y2 + 1]++;
        }

        for (int x = 0; x <= LENGTH; x++) {
            for (int y = 1; y <= LENGTH; y++) picture[x][y] += picture[x][y - 1];
        }
        for (int y = 0; y <= LENGTH; y++) {
            for (int x = 1; x <= LENGTH; x++) picture[x][y] += picture[x - 1][y];
        }

        int count = 0;
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < LENGTH; y++) {
                if (picture[x][y] > M) count++;
            }
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }
}
