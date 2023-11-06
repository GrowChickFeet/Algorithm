package 김현욱.additional;

import java.io.*;

public class BJ1032_명령_프롬프트 {
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        char[][] lines = new char[n][];
        for(int i=0;i<n;i++){
            lines[i] = br.readLine().toCharArray();
        }

        int len = lines[0].length;

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<len;i++){
            boolean flag = true;
            for(int j=1;j<n;j++){
                if(lines[j][i] != lines[j-1][i]){
                    flag = false;
                    break;
                }
            }
            sb.append(flag ? lines[0][i] : '?');
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
