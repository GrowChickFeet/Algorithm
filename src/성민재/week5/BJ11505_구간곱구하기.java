package 성민재.week5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ11505_구간곱구하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 곱을 구하는 횟수

        long[] num = new long[N+1]; //계산을 위해서 1만큼 크기 증가
        long pro = 1;
        for (int i = 1; i <= N; i++) {
//            pro *= Long.parseLong(br.readLine());
//            num[i] = pro;
            num[i] = Long.parseLong(br.readLine());
        }
        ArrayList<Long> list = new ArrayList<>();
        long[][] change = new long[M+K][3];
//        for (int i = 0; i < M+K; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < 3; j++) {
//                change[i][j] = Long.parseLong(st.nextToken());
//            }
//        }
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a==1){
                num[b] = c;
            }
            else {
                long ans = 1;
                for (int j = b; j <= c; j++) {
                    ans *= num[j];
                }
                sb.append(ans%1_000_000_007).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
    }
}
