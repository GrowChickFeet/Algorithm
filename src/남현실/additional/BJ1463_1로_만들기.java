package 남현실.additional;

/*
https://www.acmicpc.net/problem/1463
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1463_1로_만들기 {
    static int min = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        explore(num, 0);
        System.out.println(min);
    }

    public static void explore(int num, int cnt) {
        if (cnt >= min) {
            return;
        }
        if (num == 1) {
            min = Math.min(min, cnt);
            return;
        }

        if (num % 3 == 0) {
            explore(num / 3, cnt + 1);
        }
        if (num % 2 == 0) {
            explore(num / 2, cnt + 1);
        }
        explore(num - 1, cnt + 1);
    }


}
