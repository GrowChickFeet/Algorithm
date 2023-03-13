package 남현실.week1;

/*
https://www.acmicpc.net/problem/25497
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ25497_기술_연계마스터_임스 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        Stack<Character> LRStack = new Stack<Character>();
        Stack<Character> SKStack = new Stack<Character>();

        int count = 0;
        for (char skill : br.readLine().toCharArray()) {
            // 사전 기술
            if (skill == 'L') {
                LRStack.add(skill);
                continue;
            } else if (skill == 'S') { // 사전 기술
                SKStack.add(skill);
                continue;
            }

            // 연계 기술 L-R
            if (skill == 'R' && LRStack.size() == 0) {
                break;
            }
            if (skill == 'R' && LRStack.peek() == 'L') {
                LRStack.pop();
                count++;
                continue;
            }

            // 연계 기술 S-K
            if (skill == 'K' && SKStack.size() == 0) {
                break;
            } else if (skill == 'K' && SKStack.peek() == 'S') {
                SKStack.pop();
                count++;
                continue;
            }

            // 연계 없는 기술 (1~9)
            count++;
        }

        System.out.println(count);

        br.close();
    }
}
