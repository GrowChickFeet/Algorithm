package 남현실.week1;

/*
https://www.acmicpc.net/problem/13699
 */

import java.math.BigInteger;
import java.util.Scanner;

/*
- for 문을 이용하여 점화식 표현
- int, long, double을 사용해보았으나 전부 overflow가 발생하여 BigInteger 사용
 */
public class BJ13699_점화식 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        String[] arr = new String[N + 1];

        BigInteger temp;
        BigInteger tg1, tg2;
        for (int i = 0; i <= N; i++) {
            if (arr[0] == null) {
                arr[0] = "1";
                continue;
            }
            temp = new BigInteger("0");

            for (int j = 0; j < i; j++) {
                tg1 = new BigInteger(arr[j]);
                tg2 = new BigInteger(arr[i - j - 1]);
                temp = temp.add(tg1.multiply(tg2));
            }
            arr[i] = temp.toString();
        }

        System.out.println(arr[N]);
    }
}
