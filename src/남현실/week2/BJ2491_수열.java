package 남현실.week2;

/*
https://www.acmicpc.net/problem/2491
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2491_수열 {
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수열의 길이
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxCnt = 1;
        int ascCnt, descCnt;
        int ascPrev, ascCur, descPrev, descCur;
        for (int i = 0; i < N; i++) {
            ascCnt = 1;
            ascPrev = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                ascCur = arr[j];
                if (j == 0 && ascCur <= ascPrev) {
                    ascCnt++;
                }
                if (ascCur > ascPrev || j == 0) {
                    maxCnt = Math.max(maxCnt, ascCnt);
                    break;
                }
                ascPrev = ascCur;
                ascCnt++;
            }

            descCnt = 1;
            descPrev = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                descCur = arr[j];
                if (j == 0 && descCur >= descPrev) {
                    descCnt++;
                }
                if (descCur < descPrev || j == 0) {
                    maxCnt = Math.max(maxCnt, descCnt);
                    break;
                }
                descCnt++;
                descPrev = descCur;
            }
        }
        System.out.println(maxCnt);

        br.close();
    }
}
