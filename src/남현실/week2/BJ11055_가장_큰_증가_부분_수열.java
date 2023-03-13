package 남현실.week2;

/*
https://www.acmicpc.net/problem/11055
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BJ11055_가장_큰_증가_부분_수열 {
    static int N; // 수열의 크기
    static int[] arr; // 수열 데이터

    public static void main(String[] args) throws IOException {
        // 입력값 가져오기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 증가 부분 수열의 합 최대값 구하기
        System.out.println(getMax());

        br.close();
    }

    public static int getMax() { // DP 사용
        int[] maxs = new int[N]; // 해당 숫자와 증가 부분 수열의 합 최대값

        int prevMax; // 조합 가능한 이전 값들의 합 최대값
        for (int i = 0; i < N; i++) {
            // 초기화
            prevMax = 0;
            for (int j = i - 1; j >= 0; j--) { // 현재 값에서부터 거꾸로 탐색
                if (arr[j] < arr[i]) { // 증가 부분 수열 중에서 이전 값들의 합이 최대인 것 찾기
                    prevMax = Math.max(prevMax, maxs[j]);
                }
            }
            maxs[i] = arr[i] + prevMax; // 이전 최대값과 자기자신 더함
        }

        Arrays.sort(maxs); // 올림차순으로 정렬

        return maxs[N - 1]; // 가장 큰 값 리턴
    }
}
