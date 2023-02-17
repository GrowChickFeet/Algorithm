package 김현욱.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

public class BJ25497_기술연계마스터임스 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.valueOf(br.readLine());
        char[] array = br.readLine().toCharArray();

        int result = 0;

        int sCount = 0;
        int lCount = 0;

        for(int i=0;i<n;i++) {
            char c = array[i];
            if(c-'0'>=0 && c-'0'<=9) {
                result++;
            }
            else {
                if(c=='L') lCount++;
                else if(c=='S') sCount++;
                else if(c=='R') {
                    if(lCount>0) {
                        lCount--;
                        result++;
                    }
                    else {
                        break;
                    }
                }
                else {
                    if(sCount>0) {
                        sCount--;
                        result++;
                    }
                    else {
                        break;
                    }
                }
            }
        }

        bw.write(Integer.toString(result));
        bw.close();
        br.close();
    }
}
