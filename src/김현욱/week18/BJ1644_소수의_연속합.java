package 김현욱.week18;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ1644_소수의_연속합 {
    static List<Integer> primes = new ArrayList<>();
    static final int MAX = 4_000_000;

    static boolean[] visited = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 2; i <= MAX; i++) {
            if (!visited[i]) {
                primes.add(i);
                for (int j = i; j <= MAX; j += i) {
                    visited[j] = true;
                }
            }
        }

        int answer = 0;
        int left = 0;
        long sum = 0;
        for (int right = 0; right < primes.size() && primes.get(right) <= n; right++) {
            sum+=primes.get(right);
            while(sum>n && left < right){
                sum -= primes.get(left++);
            }

            if(sum == n){
                answer++;
            }
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
