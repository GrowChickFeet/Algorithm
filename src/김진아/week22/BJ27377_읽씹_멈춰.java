package 김진아.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.math.BigInteger;

public class BJ27377_읽씹_멈춰 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            long n = Long.parseLong(reader.readLine());
            String[] input = reader.readLine().split(" ");
            BigInteger s = new BigInteger(input[0]);
            BigInteger t = new BigInteger(input[1]);

            BigInteger time = BigInteger.ZERO;
            while (n > 0) {
                if (n % 2 != 0) {
                    time = time.add(s);
                    n--;
                } else {
                    BigInteger now = new BigInteger(String.valueOf(n));
                    if (now.divide(BigInteger.TWO).multiply(s).compareTo(t) > 0) time = time.add(t);
                    else time = time.add(s.multiply(now.divide(BigInteger.TWO)));
                    n /= 2;
                }
            }

            builder.append(time).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
