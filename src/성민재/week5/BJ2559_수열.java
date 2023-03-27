package 성민재.week5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ2559_수열 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken()); //누적합 구하기
        }
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            cnt += num[i];
        }
        int start = 0;
        int end = K;
        int max = cnt;
        while(end <= N-1){
            cnt += num[end++];
            cnt -= num[start++];
            max = Math.max(cnt, max);
        }
        
//        int sector = 0;
//        for (int i = 0; i < N; i++) {
//            sector += Integer.parseInt(st.nextToken()); //누적합 구하기
//            num[i] = sector;
//        }
//
//        //2 point 방식
//        int left = 0;
//        int right = K;
//
//        int max = num[K-1]; //첫번째 연산값을 구한다. 0부터 k까지 이므로 앞에서 뺄값이 없으므로 k-1만 구한다.
//        for (int n = 0; n < N-K; n++) {
//            int cal = num[right++] - num[left++]; // 앞에서 뒤에꺼 빼주는데 하나 더 뒤에꺼를 빼줘야 한다.
//            max = Math.max(cal,max);
//        }

        sb.append(max);
        bw.write(sb.toString());
        bw.close();
    }
}
