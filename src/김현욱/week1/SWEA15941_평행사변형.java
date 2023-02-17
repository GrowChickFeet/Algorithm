package 김현욱.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA15941_평행사변형 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T=Integer.valueOf(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            int n = Integer.valueOf(br.readLine());
            sb.append('#').append(test_case).append(' ').append(n*n).append('\n');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}
