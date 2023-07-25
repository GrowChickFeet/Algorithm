package 박지영.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 에라토스테네스의 체로 소수찾기
 * : 2부터 배수를 지우면서 가기
 */
public class BJ2960_에라토스테네스의체 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] arr = new boolean[1001];
        Arrays.fill(arr, true);


        System.out.print(find(N, K, arr));
    }

    static int find(int N, int K, boolean[] arr) {
        int count = 0;  // 지워지는 수의 개수
        for (int i = 2; i <= N; i++) {
            if(!arr[i]) continue;

            for (int j = 1; j <= N/i; j++) {
                if(!arr[i*j]) continue;
                arr[i*j] = false;
                count++;
                if (count == K) {
                    return i*j;
                }
            }
        }

        return 0;
    }

}