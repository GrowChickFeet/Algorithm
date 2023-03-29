package 박지영.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11505_구간곱구하기 {
    static int N, M, K;
    static long[] tree;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new long[N*4];
        initTree(0, N-1, 1);

        System.out.println(Arrays.toString(tree));
        System.out.println(multi(1, N-1, 1, 4, 5));
        update(1, 1, N-1, 3, 6/arr[3-1]);
        System.out.println(Arrays.toString(tree));
    }

    static void initTree(int start, int end, int index) {
        if (start == end) { // 가장 밑의 노드
            tree[index] = arr[start];
            return;
        }
        int mid = (start+end)/2;
        initTree(start, mid, index*2);
        initTree(mid+1, end, index*2+1);
        tree[index] = tree[index*2] * tree[index*2+1];
    }

    // left, right : left * ... * right 의 곱
    static long multi(int start, int end, int index, int left, int right) {
        if (end < left || right < start) return 1;
        else if (left <= start && end <= right) return tree[index];
        else {
            return multi(start, (start+end)/2, index * 2, left, right) * multi((start+end)/2+1, end, index*2+1, left, right);
        }
    }

    static void update(int node , int start, int end, int index, int diff) {
        if (end < index || index < start) return;
        tree[node] *= diff;
        if (start != end) {     // 밑에 더 노드가 있다면
            update(node*2, start, (start+end)/2, index, diff);
            update(node*2+1, (start+end)/2+1, end, index, diff);
        }
    }
}
