package 박지영.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 배열을 저장해두고 거꾸로 가면서 더식 작은 값이면 이익을 저장하도록 풀이
 */
public class BJ11501_주식 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            // 배열 생성
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            // 거꾸로 가면서 더 작은값이면 차이만큼 이익, 큰값이면 업데이트
            int tmp = arr[N-1];
            long result = 0;
            for (int j = N-2; j >= 0; j--) {
                if (tmp > arr[j]) {
                    result += tmp - arr[j];
                } else {
                    tmp = arr[j];
                }
            }

            sb.append(result).append("\n");
        }

        System.out.print(sb.toString());
    }
}
