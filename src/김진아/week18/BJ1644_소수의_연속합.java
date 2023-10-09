package 김진아.week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ1644_소수의_연속합 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) primes.add(i);
        }

        int length = primes.size();
        int count = 0;
        int sum = 0;
        int end = 0;

        for (int start = 0; start < length; start++) {
            if (sum >= N || end == length) sum -= primes.get(start - 1);
            while (end < length && sum < N) sum += primes.get(end++);
            if (sum == N) count++;
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

    static boolean isPrime(int N) {
        int mid = (int) Math.sqrt(N);
        for (int i = 2; i <= mid; i++) {
            if (N % i == 0) return false;
        }
        return true;
    }

}
