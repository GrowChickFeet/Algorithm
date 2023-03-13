package 남현실.week3;

/*
https://www.acmicpc.net/problem/15684
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ15684_사다리_조작 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int H = Integer.parseInt(temp[1]);
        int M = Integer.parseInt(temp[2]);

        int[][] ladders = new int[N][M];

        int x, y;
        for (int i = 0; i < H; i++) {
            temp = br.readLine().split(" ");
            x = Integer.parseInt(temp[0]) - 1;
            y = Integer.parseInt(temp[1]) - 1;
//            ladders[x][y] =
        }


        br.close();
    }
}
