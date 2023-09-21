import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] distance = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) distance[i][j] = 1000;
        }

        for (int i = 0; i < N; i++) distance[i][i] = 0;

        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int friend1 = Integer.parseInt(input[0]) - 1;
            int friend2 = Integer.parseInt(input[1]) - 1;
            distance[friend1][friend2] = 1;
            distance[friend2][friend1] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) distance[j][k] = Math.min(distance[j][i] + distance[i][k], distance[j][k]);
            }
        }

        int minFriend = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) sum += distance[i][j];
            if (sum < min) {
                minFriend = i;
                min = sum;
            }
        }

        writer.write(Integer.toString(minFriend + 1));

        writer.close();
        reader.close();
    }

}
