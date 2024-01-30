package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ10709_기상캐스터 {

    static int H;
    static int W;
    static boolean[][] existCloud;

    static int[][] cloudTime;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        H = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);

        existCloud = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            String line = reader.readLine();
            for (int j = 0; j < W; j++) existCloud[i][j] = line.charAt(j) == 'c';
        }

        observeCloud();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) builder.append(cloudTime[i][j]).append(" ");
            builder.append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void observeCloud() {
        cloudTime = new int[H][W];
        for (int i = 0; i < H; i++) {
            int time = -1;
            for (int j = 0; j < W; j++) {
                if (existCloud[i][j]) time = 0;
                else if (time >= 0) time++;
                cloudTime[i][j] = time;
            }
        }
    }

}
