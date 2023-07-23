package 박지영.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 세그먼트 트리로 구간곱 구하기
 */
public class BJ11505_구간곱구하기 {
    static int N, M, K;
    static int[] arr;
    static long[] tree;
    final static int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        arr = new int[N+1];
        tree = new long[N * 4];
        for (int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(1, N, 1);

        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {        // 숫자 바꾸기
                update(1, N, 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {        // 구간 곱 구하기
                sb.append(multi(1, N, 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())))
                        .append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    // target : 바꾸고자 하는 index, diff : 바꾸고자 하는 값
    static long update(int start, int end, int index, int target, int diff) {
        // 범위 밖일 때
        if (target < start || target > end) return tree[index];
        // 리프 노드 -> 업데이트
        if (start == end) return tree[index] = diff;
        int mid = (start+end)/2;
        return tree[index] = update(start, mid, index*2, target, diff) * update(mid+1, end, index*2+1, target,diff) % MOD;
    }

    // left, right: 구하려고 하는 범위
    static long multi(int start, int end, int index, int left, int right) {
        // 범위 밖에 있을 때
        if (left > end || right < start) return 1;
        // 범위 안에 있을 때
        if (left <= start && end <= right) return tree[index];
        int mid = (start+end)/2;
        return multi(start, mid, index * 2, left, right) * multi(mid+1, end, index*2+1, left, right) % MOD;
    }
    static long init(int start, int end, int index) {
        if (start == end) return tree[index] = arr[start];       // 가장 끝에 도달했음
        int mid = (start+end) / 2;
        return tree[index] = init(start, mid, index*2) * init(mid+1, end, index*2 + 1) % MOD;
    }
}