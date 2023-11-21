package 김현욱.additional;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ16566_카드_게임 {
    public static int[] parent;
    public static int[] arr;

    public static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    public static void init(int m) {
        parent = new int[m];
        for (int i = 0; i < m; i++) {
            parent[i] = i;
        }
        arr = new int[m];
    }

    public static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int middle = (left + right) >> 1;

            if (target < arr[middle]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        init(m);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);//오름차순 정리

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            int value = Integer.parseInt(st.nextToken());

            int index = upperBound(arr, value);
            sb.append(arr[find(index)]).append('\n');
            union(find(index), find(Math.min(find(index) + 1, m - 1)));
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
