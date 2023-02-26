package 김현욱.week2;

import java.io.*;
import java.util.StringTokenizer;

public class BJ2491_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        int leftCount = 0;
        int rightCount = 0;
        for (int left = 1, right = n; left <= n && right >= 1; left++, right--) {
            if (arr[left] >= arr[left - 1]) leftCount++;
            else {
                max = Math.max(max, leftCount);
                leftCount = 1;
            }

            if (arr[right] >= arr[right + 1]) rightCount++;
            else {
                max = Math.max(max, rightCount);
                rightCount = 1;
            }
        }

        max = Math.max(max,Math.max(leftCount,rightCount));

        bw.write(Integer.toString(max));
        br.close();
        bw.close();
    }
}
