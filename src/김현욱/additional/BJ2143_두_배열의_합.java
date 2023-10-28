package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2143_두_배열의_합 {
    public static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int middle = (left + right) >> 1;

            if (list.get(middle) >= target) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return right;
    }

    public static int upperBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int middle = (left + right) >> 1;

            if (list.get(middle) <= target) {
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

        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arrA = new int[n + 1];
        List<Integer> sumA = new ArrayList<>(n * n + 10);
        for (int i = 1; i <= n; i++) {
            arrA[i] = Integer.parseInt(st.nextToken()) + arrA[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                sumA.add(arrA[i] - arrA[j]);
            }
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arrB = new int[m + 1];
        List<Integer> sumB = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            arrB[i] = Integer.parseInt(st.nextToken()) + arrB[i - 1];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < i; j++) {
                sumB.add(arrB[i] - arrB[j]);
            }
        }

        Collections.sort(sumA);
        Collections.sort(sumB);

        long result = 0;
        for (int i = 0; i < sumA.size(); i++) {
            int value = sumA.get(i);
            int lowerIndex = lowerBound(sumB, t - value);
            if (0 <= lowerIndex && lowerIndex < sumB.size() &&
                    sumB.get(lowerIndex) == t - value) {
                int upperIndex = upperBound(sumB, t - value);
                result += (upperIndex - lowerIndex);
            }
        }
        bw.write(Long.toString(result));
        br.close();
        bw.close();
    }
}
