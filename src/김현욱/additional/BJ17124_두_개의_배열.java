package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17124_두_개의_배열 {
    public static int lowerBound(int[] array, int value) {
        int left = 0;
        int right = array.length;

        while (left < right) {
            int middle = (left + right) >> 1;
            if (array[middle] <= value) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] A = new int[n];
            int[] B = new int[m];
            long sum = 0;

            //A배열 입력하며 노드리스트에 저장
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A);

            //B배열 입력후 정렬
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B);

            for (int i = 0; i < n; i++) {
                int value = A[i];

                int lowerIndex = lowerBound(B, value);
                if (lowerIndex >= B.length) lowerIndex--;
                if (lowerIndex > 0) {
                    int v1 = B[lowerIndex - 1];
                    int v2 = B[lowerIndex];

                    if (Math.abs(value - v1) <= Math.abs(value - v2)) {
                        lowerIndex--;
                    }
                }
                sum += B[lowerIndex];
            }
            sb.append(sum).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}