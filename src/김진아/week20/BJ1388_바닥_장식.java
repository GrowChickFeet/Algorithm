package 김진아.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1388_바닥_장식 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] room = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < M; j++) room[i][j] = line.charAt(j) == '-' ? 1 : -1;
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (room[i][j] == 0) continue;

                if (room[i][j] == 1) {
                    for (int k = j; k < M; k++) {
                        if (room[i][k] != 1) break;
                        room[i][k] = 0;
                    }
                } else {
                    for (int k = i; k < N; k++) {
                        if (room[k][j] != -1) break;
                        room[k][j] = 0;
                    }
                }

                count++;
            }
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
