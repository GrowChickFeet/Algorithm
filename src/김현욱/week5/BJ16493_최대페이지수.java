package 김현욱.week5;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ16493_최대페이지수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] array = new int[m][2];
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());//소요 일
            array[i][1] = Integer.parseInt(st.nextToken());//페이지 수
        }
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        int[] dp = new int[n+1];
        for(int j=0;j<m;j++){
            int dur = array[j][0];
            int page = array[j][1];
            for(int i=n;i>=dur;i--){
                dp[i] = Math.max(dp[i],dp[i-dur]+page);
            }
        }

        bw.write(Integer.toString(dp[n]));
        br.close();
        bw.close();
    }
}
