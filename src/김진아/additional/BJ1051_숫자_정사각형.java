package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1051_숫자_정사각형 {

    static int N;
    static int M;
    static int[][] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        nums = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < M; j++) nums[i][j] = line.charAt(j) - '0';
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) max = Math.max(size(i, j), max);
        }

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

    static int size(int x, int y) {
        int size = 1;
        for (int i = 1; x + i < N && y + i < M; i++) {
            if (nums[x + i][y] == nums[x][y] && nums[x][y + i] == nums[x][y] && nums[x + i][y + i] == nums[x][y]) size = (i + 1) * (i + 1);
        }
        return size;
    }

}
