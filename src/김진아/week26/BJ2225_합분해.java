package 김진아.week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.math.BigInteger;

public class BJ2225_합분해 {

    final static String MOD = "1000000000";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]) - 1;

        N += K;
        if (N - K < K) K = N - K;

        BigInteger count = BigInteger.ONE;
        for (int i = N; i > N - K; i--) count = count.multiply(new BigInteger(Integer.toString(i)));
        for (int i = K; i > 1; i--) count = count.divide(new BigInteger(Integer.toString(i)));

        writer.write(count.mod(new BigInteger(MOD)).toString());

        writer.close();
        reader.close();
    }

}
