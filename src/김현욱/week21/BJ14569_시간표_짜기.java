package 김현욱.week21;

import java.io.*;
import java.util.StringTokenizer;

public class BJ14569_시간표_짜기 {
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[] lectures = new long[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                long v = Long.parseLong(st.nextToken());
                lectures[i] = lectures[i] | ((long)1 << v);
            }
        }
        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            long value = 0;
            for (int j = 0; j < num; j++) {
                long v = Long.parseLong(st.nextToken());
                value = value | ((long)1 << v);
            }
            value = ~value;

            int result = 0;
            for(int j=0;j<n;j++){
                if((lectures[j] & value) == 0){
                    result++;
                }
            }
            sb.append(result).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
