package 남현실.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*

각 숫자(32비트)의 비트 별 1값을 cnt를 해서 가장 긴 큰값이 답
- &연산으로 1이 나오려면 전부 1로 이루어져야하고
- 비트는 0, 1로만 구성되어 있으니
- bit값이 1인 것에 대한 cnt를 세면 된다

5
5 6 7 11 15
---
4
 */
public class BJ23630_가장_긴_부분_수열_구하기 {
    static final int intSize = 32;
    static int N;
    static int[] bitCnt; // 32bit의 각 자리에 대한 1 count

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        bitCnt = new int[intSize];

        String[] temp = br.readLine().split(" ");

        // init by first
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(temp[i]);
//            countBit(num);
            countBit2(num);
        }

        int max = 0;
        for (int i = 0; i < intSize; i++) {
            max = Math.max(max, bitCnt[i]);
        }
        System.out.println(max);
        br.close();
    }

    static void countBit(int num){
        int tg = 1;
        for (int i = 0; i < intSize; i++) {
            if((num & tg) > 0) {
                bitCnt[i] += 1;
            }
            tg *= 2;
        }
    }

    static void countBit2(int num) {
        int i = 0;
        while (num >0) {
            if(num%2 == 1){
               bitCnt[i]++;
            }
            i++;
            num /= 2;
        }
    }

}
