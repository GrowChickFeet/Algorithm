package 김현욱.week13;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ17845_수강과목 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[k][2];

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1],o2[1]);//공부시간을 기준으로 오름차순 정렬
            }
        });

        int result = 0;
        int[] dp = new int[n+1];
        for(int i=0;i<k;i++){
            int time = arr[i][1];//공부시간
            int importance = arr[i][0];//중요도

            for(int t = n;t>=time;t--){
                dp[t] = Math.max(dp[t],dp[t-time]+importance);
                result = Math.max(dp[t],result);
            }
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
