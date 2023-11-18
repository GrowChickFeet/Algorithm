package 김진아.week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.math.BigInteger;
import java.util.HashMap;

public class BJ20943_카카오톡 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<Double, Integer> gradients = new HashMap<>();
        int parallelX = 0;
        int parallelY = 0;

        BigInteger count = BigInteger.ZERO;

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            double a = Double.parseDouble(input[0]);
            double b = Double.parseDouble(input[1]);

            if (a == 0) {
                parallelX++;
                count = count.add(new BigInteger(String.valueOf(i - parallelX + 1)));
            } else if (b == 0) {
                parallelY++;
                count = count.add(new BigInteger(String.valueOf(i - parallelY + 1)));
            } else {
                double gradient = -a / b;
                gradients.put(gradient, gradients.getOrDefault(gradient, 0) + 1);
                count = count.add(new BigInteger(String.valueOf(i - gradients.get(gradient) + 1)));
            }
        }

        writer.write(count.toString());

        writer.close();
        reader.close();
    }

}
