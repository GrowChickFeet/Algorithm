import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ7795_먹을_것인가_먹힐_것인가 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            String[] input = reader.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);

            int[] A = new int[N];
            input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) A[j] = Integer.parseInt(input[j]);

            int[] B = new int[M];
            input = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) B[j] = Integer.parseInt(input[j]);

            Arrays.sort(A);
            Arrays.sort(B);

            int count = 0;

            int indexA = 0;
            int indexB = 0;
            while (indexA < N && indexB < M) {
                if (A[indexA] <= B[indexB]) indexA++;
                else {
                    count += N - indexA;
                    indexB++;
                }
            }

            builder.append(count).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
