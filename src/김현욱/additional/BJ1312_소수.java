package 김현욱.additional;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1312_소수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int result = a%b;

        for(int i=0;i<n-1;i++){
            result *= 10;
            result %=b;
        }
        result*=10;

        bw.write(Integer.toString(result/b));
        br.close();
        bw.close();
    }
}
