package 남현실.week6;
/*
https://www.acmicpc.net/problem/1965
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
[입력]
- N: 상자의 개수 (1 <= N <= 1,000)
- i: 상자의 크기 (1 <= i <= 1,000)

[조건]
- 정육면체 모양의 상제 일렬로 있음
- 앞 상자의 크기 < 뒷 상자 크기
    -> 앞 상자 뒷 상자에 넣을 수 있음

[출력]
- 한 줄에 넣을 수 있는 최대 상자 개수

[풀이]
최장 증가 수열 길이 찾기
- LIS [성공]
- 이진 검색 [실패]
 */
public class BJ1965_상자넣기 {
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] LIS = new int[N];

        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }

        // LIS을 통해 최장 증가 수열 길이 찾기
        int max = 0;
        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                }
            }
            max = Math.max(max, LIS[i]);
        }

        // 결과 출력
        System.out.println(max);



        // 이진 검색으로 찾기

        int[] seq = new int[N];

        int size = 0;

        int idx;
        for (int i = 0; i < N; i++) {
            idx = Arrays.binarySearch(seq, 0, size, arr[i]); // -insertPoint -1
            idx = Math.abs(idx)-1;

            seq[idx] = arr[i];

            if(size == idx) {
                size++;
            }
        }

        System.out.println(size);

    }
}