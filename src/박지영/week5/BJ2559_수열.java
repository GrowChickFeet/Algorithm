package 박지영.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2559_수열 {
    static int N, K;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N];


        long result = Long.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long tmp = 0;
        for (int j = 0; j < K; j++) {       // 초기값구하기
            tmp += arr[j];
        }
        result = Math.max(result, tmp);
        // 슬라이딩 윈도우
        for (int j = 0; j < N - K ; j++) {
            tmp -= arr[j];
            tmp += arr[j + K];
            result = Math.max(result, tmp);
        }
        System.out.print(result);

    }
}
