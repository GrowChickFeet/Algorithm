package 김현욱.week5;

import java.io.*;
import java.util.StringTokenizer;

public class BJ2559_수열 {
    public static int twoPointer(int[] arr,int n,int k){
        int left = 1;
        int right=1;
        int sum = 0;//k일의 합을 저장하는 변수
        for(;right<=k;right++){//초기 k일의 합을 구해줌
            sum+=arr[right];//k일동안의 합을 저장
        }
        int result = sum;//결과값 저장

        for(;right<=n;right++,left++){//left,right를 옮겨가며
            sum = sum - arr[left] + arr[right];//값을 갱신
            result = Math.max(result,sum);//결과값을 갱신
        }
        return result;
    }
    public static int cumulativeSum(int[] arr,int n,int k){
        int[] sum = new int[n+1];//누적합 배열을 할당
        for(int i=1;i<=n;i++){
            sum[i] = sum[i-1]+arr[i];//누적합 저장
        }
        int result = -Integer.MAX_VALUE;//결과값이 최대가 되는값이기 떄문에 최솟값으로 저장
        for(int i=k;i<=n;i++){//k일~n일까지 확인
            int temperatureSum = sum[i] - sum[i-k];//누적합을 이용하여 k일간의 온도 합을 구함
            result = Math.max(result,temperatureSum);//갱신
        }

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }


        bw.write(Integer.toString(twoPointer(arr,n,k)));
        br.close();
        bw.close();
    }
}
