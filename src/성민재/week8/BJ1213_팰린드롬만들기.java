package 성민재.week8;

import java.io.*;
import java.util.*;

public class BJ1213_팰린드롬만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] alpha = new int[26];
        String input = br.readLine();
        String res = "";

        for(int i = 0 ; i < input.length(); i++){
            int idx = input.charAt(i) - 'A';
            alpha[idx]++;
        }

        int one_cnt = 0;
        for (int i = 0; i < 26; i++) {
            if(alpha[i] % 2 == 1)
                one_cnt++;
        }

        if(one_cnt > 1) res += "I'm Sorry Hansoo";
        else{
            for(int i = 0 ; i < 26; i++){
                for (int j = 0; j < alpha[i]/2; j++) {
                    sb.append((char)(i+65));
                }
            }
            res += sb.toString();
            for (int i = 0; i < 26; i++) {
                if(alpha[i] % 2 == 1)
                    res += (char)(i+65);
            }
            res += sb.reverse().toString();;
        }
        System.out.println(res);
    }
}