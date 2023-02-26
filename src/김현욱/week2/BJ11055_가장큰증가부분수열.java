package 김현욱.week2;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ11055_가장큰증가부분수열 {
    static int[] array;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        array = new int[n+1];
        dp = new int[n+1];//dp[i] = i번째까지의 증가하는 부분수열 중에서 합이 가장 큰 값

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        for(int now = 1;now<=n;now++){//1~n번째까지 증가하는 부분수열의 합이 제일 큰것으로 갱신
            dp[now] = array[now];//now번째의 숫자가 시작인 경우
            for(int before = now-1;before >=0;before--){//이전의 인덱스들을 비교하며
                if(array[now] > array[before]){//이전것보다 현재꺼가 크다면
                    dp[now] = Math.max(dp[now],dp[before]+array[now]);//이전것의 최댓값에 array[now]를 더해준것을 갱신해준다.
                }
            }
        }
        bw.write(Integer.toString(Arrays.stream(dp).max().getAsInt()));//n번째까지의 증가하는 부분 수열 중에서 합의 최댓값을 출력
        bw.close();
        br.close();
    }
}
