package 김현욱.week9;

import java.io.*;
import java.util.StringTokenizer;

public class BJ23630_가장_긴_부분_수열_구하기 {
    static int[] bit = new int[32];

    public static void calc(int number){
        int index = 0;
        while(number > 0){
            bit[index++] += (number%2);
            number/=2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            int num = Integer.parseInt(st.nextToken());
            calc(num);
        }
        int result = 0;
        for(int i=0;i<32;i++){
            result = Math.max(result,bit[i]);
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
