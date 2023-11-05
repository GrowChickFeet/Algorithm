package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ8462_배열의_힘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[][] op = new int[t][3];
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            op[i][0] = Integer.parseInt(st.nextToken()) - 1;
            op[i][1] = Integer.parseInt(st.nextToken()) - 1;
            op[i][2] = i;
        }

        Arrays.sort(op, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int sqrtN = (int) Math.sqrt(n);
                if (o1[0] / sqrtN == o2[0] / sqrtN) {
                    return Integer.compare(o1[1], o2[1]);
                } else {
                    return Integer.compare(o1[0] / sqrtN, o2[0] / sqrtN);
                }
            }
        });

        long[] answer = new long[t];
        long[] count = new long[10_000_001];
        long s = 0;
        for (int i = op[0][0]; i <= op[0][1]; i++) {
            s -= count[arr[i]] * count[arr[i]] * arr[i];
            count[arr[i]]++;
            s += count[arr[i]] * count[arr[i]] * arr[i];
        }
        answer[op[0][2]] = s;
        int left = op[0][0];
        int right = op[0][1];

        for (int i = 1; i < t; i++) {
            int start = op[i][0];
            int end = op[i][1];

            while (left < start) {
                s -= count[arr[left]] * count[arr[left]] * arr[left];
                count[arr[left]]--;
                s += count[arr[left]] * count[arr[left]] * arr[left];
                left++;
            }
            while (left > start) {
                left--;
                s -= count[arr[left]] * count[arr[left]] * arr[left];
                count[arr[left]]++;
                s += count[arr[left]] * count[arr[left]] * arr[left];
            }
            while (right < end) {
                right++;
                s -= count[arr[right]] * count[arr[right]] * arr[right];
                count[arr[right]]++;
                s += count[arr[right]] * count[arr[right]] * arr[right];
            }
            while (right > end) {
                s -= count[arr[right]] * count[arr[right]] * arr[right];
                count[arr[right]]--;
                s += count[arr[right]] * count[arr[right]] * arr[right];
                right--;
            }
            answer[op[i][2]] = s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            sb.append(answer[i]).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
