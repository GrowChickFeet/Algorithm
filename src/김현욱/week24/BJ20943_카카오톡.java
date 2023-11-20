package 김현욱.week24;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ20943_카카오톡 {
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static long nC2(long n) {
        return (n * (n - 1)) / 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long total = nC2(n);
        Map<Double, Integer> appear = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            double inclination = 0.0;
            if (b != 0) {
                long g = gcd(Math.max(a, b), Math.min(a, b));
                inclination = -(double) (a / g) / (b / g);
            }
            appear.put(inclination, appear.getOrDefault(inclination, 0) + 1);
        }

        Set<Double> keySet = appear.keySet();
        for (Double k : keySet) {
            total -= nC2(appear.get(k));
        }

        bw.write(Long.toString(total));
        bw.close();
        br.close();
    }
}
