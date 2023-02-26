package 박지영.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2317_결혼식 {
    static int[] numbers, arr;
    static boolean[] isSelected;
    static int N, K, min;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        isSelected = new boolean[N];
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
//        System.out.println(lionSum);
        min = Integer.MAX_VALUE;
//        System.out.println(Arrays.toString(arr));
        permuation(0);
        System.out.println(min);
    }


    private static void permuation(int cnt) {
        if (cnt==N) {
            for (int i = 0; i < N; i++) {
                if (numbers[i] > N-K-1) {     // 사자

                    for (int j = i+1; j < N; j++) {
                        if (numbers[j] > N-K-1 && numbers[i] > numbers[j]) return;
                    }
                }
            }
//            System.out.println(Arrays.toString(numbers));
            sum();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            numbers[cnt] = i;
            isSelected[i] = true;
            permuation(cnt+1);
            isSelected[i] = false;
        }
    }

    private static void sum() {
        int result = 0;
        for (int i = 1; i < N; i++) {
            result += Math.abs(arr[numbers[i]] - arr[numbers[i-1]]);
        }
        min = Math.min(min, result);
//        System.out.println(result);
    }
}
