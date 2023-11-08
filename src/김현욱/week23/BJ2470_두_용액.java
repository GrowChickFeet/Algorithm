package 김현욱.week23;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2470_두_용액 {
    public static int lowerBound(int[] arr, int target) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int middle = (start + end) >> 1;
            if (arr[middle] < target) {
                start = middle+1;
            } else {
                end = middle;
            }
        }
        return end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] result = new int[2];
        int gap = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int v = arr[i];

            int index = Math.min(lowerBound(arr,-v),n-1);
            int other = -1;
            if(index != i) {
                other = arr[index];
                if (gap > Math.abs(v + other)) {
                    gap = Math.abs(v + other);
                    result[0] = v;
                    result[1] = other;
                }
            }
            index = Math.max(0,index-1);
            if(index != i) {
                other = arr[index];
                if (gap > Math.abs(v + other)) {
                    gap = Math.abs(v + other);
                    result[0] = v;
                    result[1] = other;
                }
            }
        }

        Arrays.sort(result);
        StringBuilder sb = new StringBuilder();
        sb.append(result[0]).append(' ').append(result[1]);
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
