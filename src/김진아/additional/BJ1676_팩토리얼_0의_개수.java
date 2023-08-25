import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        BigInteger fac = BigInteger.ONE;
        for (int i = 2; i <= N; i++) fac = fac.multiply(BigInteger.valueOf(i));

        int zero = 0;
        while (fac.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            fac = fac.divide(BigInteger.TEN);
            zero++;
        }

        writer.write(Integer.toString(zero));

        writer.close();
        reader.close();
    }

}
