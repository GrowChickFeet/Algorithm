package 박지영.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11055_가장큰증가부분수열 {
    static int N, max;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 재귀를 사용하였을 때 시간 초과 남
//        for (int i = 0; i < N; i++) {
//            sum(i, arr[i], arr[i]);
////            System.out.println();
//        }

        // DP 사용함
        int[] dp = new int[N];
        int max = arr[0];
        for (int i = 0; i < N; i++) {
            dp[i] = arr[i];                 // 초기값
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && dp[i] < dp[j]+arr[i]) {           // i가 커지면서 j랑 비교
                    System.out.println(Arrays.toString(dp));
                    dp[i] = dp[j] + arr[i];
                    max = Math.max(max, dp[i]);
                }
            }
        }
        System.out.println(max);

    }



    // 재귀 -> 시간초과
//    private static void sum(int index, int cnt, int sum) {
//        if (index == N-1) return;
//
//        if (cnt < arr[index+1]) {
//            System.out.print(sum+arr[index+1]+" ");
//            max = Math.max(sum+arr[index+1], max);
//            sum(index + 1, arr[index+1], sum+arr[index+1]);
//        }
//
//        sum(index+1, cnt, sum);
//    }

}
