package 김현욱.week22;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class BJ27377_읽씹_멈춰 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for (int T = 0; T < testCase; T++) {
            long n = Long.parseLong(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            BigInteger s = new BigInteger(st.nextToken());
            BigInteger t = new BigInteger(st.nextToken());
            BigInteger cost = BigInteger.ZERO;
            while (n > 0) {
                if (n % 2 == 0) {
                    BigInteger number = new BigInteger(String.valueOf(n));
                    if (number.multiply(s).divide(BigInteger.TWO).compareTo(t)>0){
                        cost = cost.add(t);
                    }
                    else{
                        cost = cost.add(s.multiply(number.divide(BigInteger.TWO)));
                    }
                    n /= 2;
                } else {
                    n--;
                    cost = cost.add(s);
                }
            }
            sb.append(cost).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
