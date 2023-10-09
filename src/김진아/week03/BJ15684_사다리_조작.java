package 김진아.week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ15684_사다리_조작 {

    static int N;
    static int H;
    static boolean[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);

        ladder = new boolean[H][N];
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            ladder[Integer.parseInt(input[0]) - 1][Integer.parseInt(input[1]) - 1] = true;
        }

        writer.write(Integer.toString(min()));

        writer.close();
        reader.close();
    }

    static int min() {
        if (isValid(ladder)) return 0;
        for (int i = 1; i <= 3; i++) {
            if (makeLadder(i, 0, 0, new int[i])) return i;
        }
        return -1;
    }

    static boolean makeLadder(int n, int index, int k, int[] selected) {
        if (index == n) {
            boolean[][] nowLadder = copy();
            for (int i = 0; i < n; i++) nowLadder[selected[i] / N][selected[i] % N] = true;
            return isValid(nowLadder);
        }

        for (int i = k; i < H * N; i++) {
            if (i % N == N - 1) continue;
            if (ladder[i / N][i % N] || (i % N > 0 && ladder[i / N][i % N - 1]) || ladder[i / N][i % N + 1]) continue;
            selected[index] = i;
            if (makeLadder(n, index + 1, i + 1, selected)) return true;
        }

        return false;
    }

    static boolean isValid(boolean[][] nowLadder) {
        for (int i = 0; i < N; i++) {
            int now = i;
            for (int j = 0; j < H; j++) {
                if (nowLadder[j][now]) now++;
                else if (now > 0 && nowLadder[j][now - 1]) now--;
            }
            if (now != i) return false;
        }
        return true;
    }

    static boolean[][] copy() {
        boolean[][] copy = new boolean[H][N];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) copy[i][j] = ladder[i][j];
        }
        return copy;
    }

}
