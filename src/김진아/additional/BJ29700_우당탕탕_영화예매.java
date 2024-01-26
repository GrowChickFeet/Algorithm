package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ29700_우당탕탕_영화예매 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);
        
        int[][] seats = new int[N][M + 1];
        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 1; j <= M; j++) seats[i][j] = line.charAt(j - 1) - '0';
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 2; j <= M; j++) seats[i][j] += seats[i][j - 1];
        }
        
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = K; j <= M; j++) {
                if (seats[i][j] - seats[i][j - K] == 0) count++;
            }
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
