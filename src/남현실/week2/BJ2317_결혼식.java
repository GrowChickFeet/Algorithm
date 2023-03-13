package 남현실.week2;

/*
https://www.acmicpc.net/problem/2317
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2317_결혼식 {
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = temp[0];
        int K = temp[1];

        int h, prevH = 0;
        int lMax = 0, lMin = INF;
        int lFirst = 0, lLast = 0;
        int diffSum = 0;
        for (int i = 0; i < K; i++) {
            h = Integer.parseInt(br.readLine());
            if (i == 0) {
                lFirst = h;
            } else if (i == K - 1) {
                lLast = h;
            }
            lMax = Math.max(lMax, h);
            lMin = Math.min(lMin, h);
            if (prevH == 0) {
                prevH = h;
                continue;
            }
            diffSum += Math.abs(prevH - h);
            prevH = h;
        }

        int pMax = 0, pMin = INF;
        for (int i = 0; i < N - K; i++) {
            h = Integer.parseInt(br.readLine());
            pMax = Math.max(pMax, h);
            pMin = Math.min(pMin, h);
        }

        int plDiff;
        int endDiff;
        if (pMax > lMax) {
            plDiff = pMax - lMax;
            if (lMax != lFirst && lMax != lLast) {
                plDiff *= 2;
            }
            endDiff = Math.min(pMax - lFirst, pMax - lLast);
            diffSum += Math.min(plDiff, endDiff);
        }
        if (pMin < lMin) {
            plDiff = lMin - pMin;
            if (lMin != lFirst && lMin != lLast) {
                plDiff *= 2;
            }
            endDiff = Math.min(lFirst - pMin, lLast - pMin);
            diffSum += Math.min(plDiff, endDiff);
        }
        System.out.println(diffSum);

        br.close();
    }
}
