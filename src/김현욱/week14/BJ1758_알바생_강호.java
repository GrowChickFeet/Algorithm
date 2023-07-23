package 김현욱.week14;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class BJ1758_알바생_강호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] tips = new int[n];
        for(int i=0;i<n;i++){
            tips[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(tips);
        long sum = 0;
        for(int i=n-1;i>=0;i--){
            sum += Math.max(0,tips[i] - (n-i-1));
        }
        bw.write(Long.toString(sum));
        br.close();
        bw.close();
    }
}
