package 박지영.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2491_수열 {
    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // ------ 입력 끝 ------

        int tmpCount = 1;
        int result = 1;
        for (int i = 0; i < N-1; i++) {         // 연속해서 커짐
            if (arr[i] <= arr[i+1]){            // 다음값보다 작거나 같음 (커지는 중)
                tmpCount++;
                result = Math.max(tmpCount, result);
            } else {
                tmpCount = 1;
            }
        }

        tmpCount = 1;
        for (int i = 0; i < N-1; i++) {         // 연속해서 작아짐
            if (arr[i] >= arr[i+1]){            // 다음값보다 크거나 같음 (작아지는 중)
                tmpCount++;
                result = Math.max(tmpCount, result);
            } else {
                tmpCount = 1;
            }
        }
        System.out.println(result);
    }
}
