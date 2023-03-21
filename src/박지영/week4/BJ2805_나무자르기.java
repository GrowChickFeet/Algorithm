package 박지영.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2805_나무자르기 {
    static int N, M;
    static long result;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        visited = new boolean[max];

//        cut(max/2, max/2);
        int start = 0;
        int end = max;
        while(end >= start) {
            int mid = (start+end)/2;
            long sum = 0;
            for (int j = 0; j < N; j++) {
                sum += arr[j]-mid > 0 ? arr[j]-mid : 0;
            }
//            System.out.println(start+":"+mid+":"+end+":"+sum);
            if (sum == M) {
                result = mid;
                break;
            }
            if (sum > M) {
                result = Math.max(result, mid);
                start = mid;
            } else {
                end = mid-1;
            }

        }


        System.out.println(result);
    }

//    static void cut(int length, int range) {
//        if (length >= visited.length || length < 0) return;
//        if (visited[length]) return;
//        visited[length] = true;
//        long sum = 0;
//        for (int j = 0; j < N; j++) {
//            sum += arr[j]-length > 0 ? arr[j]-length : 0;
//        }
//
////        System.out.println("length: "+length+", range: "+range +", sum: "+sum);
//        if (sum >= M) {
//            result = Math.max(result, length);
//            if (range==1) range++;
//            cut(length+range/2, range/2);
//        } else {
//            if (range==1) range++ ;
//            cut(length-range/2, range/2);
//        }
//    }
}
