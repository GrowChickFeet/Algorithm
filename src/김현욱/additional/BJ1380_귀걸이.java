package 김현욱.additional;

import java.io.*;
import java.util.StringTokenizer;

public class BJ1380_귀걸이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int round = 1;
        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            String[] names = new String[n+1];
            for(int i=1;i<=n;i++){
                names[i] = br.readLine();
            }
            boolean[] notReceive = new boolean[n+1];
            for(int i=0;i<2*n-1;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                String c = st.nextToken();
                notReceive[num] = !notReceive[num];
            }
            for(int i=1;i<=n;i++){
                if(notReceive[i]){
                    sb.append(round++).append(' ').append(names[i]).append('\n');
                    break;
                }
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
