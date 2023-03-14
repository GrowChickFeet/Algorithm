package 김현욱.additional;

import java.io.*;
import java.util.StringTokenizer;

public class BJ1783_병든나이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int result = -1;
        if(n==1) result = 1;
        else if(n==2){
            result = Math.min(4,(m+1)/2);
        }
        else if(m<7){
            result = Math.min(4,m);
        }
        else
            result = m-2;
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
