package 김현욱.week15;

import java.io.*;
import java.util.StringTokenizer;

public class BJ2960_에라토스테네스의체 {
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] check = new boolean[n+1];
        int count = 0;
        int result = 0;
        for(int i=2;i<=n;i++){
            if(!check[i]) {
                for (int j = i; j<=n;j+=i){
                    if(!check[j]){
                        check[j] = true;
                        count++;
                    }
                    if(count == k) {
                        result = j;
                        break;
                    }
                }
            }
            if(count == k) break;
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
