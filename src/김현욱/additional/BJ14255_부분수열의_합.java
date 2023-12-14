package 김현욱.additional;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ14255_부분수열의_합 {
    public static void dfs(Set<Integer> set, int[] arr, int sum, int index) {
        set.remove(sum);
        if (index == arr.length) {
            return;
        }

        dfs(set, arr, sum, index + 1);
        dfs(set, arr, sum + arr[index], index + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= 20 * 100000; i++) {
            set.add(i);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(set, arr, 0, 0);
        int result = set.stream().sorted().findFirst().get();
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
