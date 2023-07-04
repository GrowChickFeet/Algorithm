package 김현욱.week13;

import java.io.*;
import java.util.StringTokenizer;

public class BJ15831_준표의조약돌 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        char[] load = br.readLine().toCharArray();

        int result = 0;//최대길이
        int left = -1;

        int white = 0;
        int black = 0;

        for(int right=0;right<n;right++){
            while(black>b){
                int lValue = load[++left];
                if(lValue == 'W') white--;
                else black--;
            }

            int rValue = load[right];
            if(rValue == 'W') white++;
            else black++;

            if(black<=b && white>=w){
                result = Math.max(result,right-left);
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
