package 김현욱.week4;

import java.io.*;
import java.util.StringTokenizer;

public class BJ2805_나무자르기 {
    static int n,m;
    static int[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        trees = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            trees[i]=Integer.parseInt(st.nextToken());
        }

        long result = binarySearch();
        bw.write(Long.toString(result));
        br.close();
        bw.close();
    }

    private static long binarySearch() {
        long left = 0;
        long right = 1_000_000_000;

        while(left<=right){
            long middle = (left+right)/2;
            if(check(middle)){
                left = middle+1;
            }
            else {
                right = middle-1;
            }
        }

        return right;
    }

    private static boolean check(long middle) {
        long remain = 0;
        for(int tree:trees){
            remain+=Math.max(0,tree-middle);
        }
        return remain >= m;
    }
}
