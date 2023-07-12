import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.math.BigInteger;

public class Main {

    static BigInteger[] fac;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        fac = new BigInteger[N + M];
        fac[0] = BigInteger.valueOf(1);
        fac[1] = BigInteger.valueOf(1);

        writer.write(count(N, M, K).toString());

        writer.close();
        reader.close();
    }

    static BigInteger count(int N, int M, int K) {
        if (K == 0) return fac(N + M - 2).divide(fac(N - 1)).divide(fac(M - 1));
        int row = (K - 1) / M;
        int column = (K - 1) % M;
        return fac(row + column).divide(fac(row)).divide(fac(column))
                .multiply(fac(N - row + M - column - 2)).divide(fac(N - row - 1)).divide(fac(M - column - 1));
    }

    static BigInteger fac(int n) {
        if (fac[n] == null) fac[n] = BigInteger.valueOf(n).multiply(fac(n - 1));
        return fac[n];
    }

}
