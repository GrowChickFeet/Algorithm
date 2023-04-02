package 박지영.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 상자넣기로는 제출이 되는데 이진으로 하면 정답이 나오지 않음.. 왜..?
public class BJ11053_가장긴증가하는부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] LIS = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {           // 입력받기
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < n; i++) {       // 최장증가부분수열
            LIS[i] = 1;         // 자기자신
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && LIS[i] < LIS[j]+1 ) {        // 증가하는 숫자이면
                    LIS[i] = LIS[j] +1;     // 카운트 증가
                }
            }
            max = Math.max(max, LIS[i]);
        }
        System.out.print(max);
    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//
//        int[] arr = new int[N];
//        int[] LIS = new int[N];
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(st.nextToken());
//            if (arr[i] == 0) System.out.println("00000");
//        }
//
//        int size = 0;
//        for (int i = 0; i < N; i++) {
//            int tmp = Arrays.binarySearch(LIS, 0, size, arr[i]);
//            if (tmp == 0) continue;
//            tmp = Math.abs(tmp) -1;
//            LIS[tmp] = arr[i];
//            if (size == tmp) {
//                size++;
//            }
////            System.out.println(Arrays.toString(LIS));
//        }
//        System.out.println(size);
//    }
}

