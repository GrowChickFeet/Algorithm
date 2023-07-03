package 김현욱.week12;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10942_팰린드롬 {
    static int n,m;
    static int[] arr;
    static int[][] dp;//dp[s][e] = s~e까지 팰린드롬인지 아닌지, 맞으면 1,아니면 0, 초기값 -1

    static int getPalindrome(int s,int e){
        if(dp[s][e] != -1){//이미 구해진 값이라면
            return dp[s][e];
        }
        if(s>=e){//시작과 끝이 같으면 무조건 팰린드롬
            return dp[s][e] = 1;
        }

        return dp[s][e] = (arr[s]==arr[e] && (getPalindrome(s+1,e-1) == 1) ) ? 1 : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n][n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i],-1);
        }


        m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;

            sb.append(getPalindrome(s,e)).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
