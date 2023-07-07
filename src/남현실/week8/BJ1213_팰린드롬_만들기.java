package 남현실.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BJ1213_팰린드롬_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] temp = br.readLine().toCharArray();
        final int alphaLen = 26;

        int[] cntInfo = new int[alphaLen + 1]; // 알파벳 순서대로 개수 세기
        boolean[] isOdd = new boolean[alphaLen + 1]; // 알파벳 순서대로 홀수 세기

        int len = temp.length;
        int idx;
        for (char c : temp) {
            idx = getIdx(c);
            cntInfo[idx] += 1;
            isOdd[idx] = !isOdd[idx];
        }

        StringBuilder result = new StringBuilder();
        StringBuilder front = new StringBuilder();
        String mid = "";
        int oddCnt = 0;
        for (int i = 0; i <= alphaLen; i++) {
            int cnt = cntInfo[i];
            if(cnt == 0) { // 문자 없으면 넘어가기
                continue;
            }
            if (isOdd[i]) { // 홀수 세기
                oddCnt += 1;
            }
            if((len%2 == 0 && oddCnt > 0) || // 문자열 길이가 짝수일때 홀수가 존재하거나,
                (len%2 == 1 && oddCnt > 1)) { // 길이가 홀수일때 홀수가 2이상이면 => Fail 처리
                System.out.println("I'm Sorry Hansoo");
                return;
            }
            char c = getChar(i);
            if(isOdd[i]) { // 홀수면 가운데 문자로 지정
                mid = String.valueOf(c);
            }
            for (int j = 0; j < cnt/2 ; j++) { // 절반만큼 반복해서 앞 문자열에 넣어주기
                front.append(c);
            }
        }

        result.append(front)
                .append(mid)
                .append(front.reverse());

        System.out.println(result);
    }

    static int getIdx(char c) {
        return (int) (c - 'A');
    }

    static char getChar(int idx) {
        return (char) (idx + 'A');
    }
}
