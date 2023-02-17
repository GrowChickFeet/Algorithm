package 박지영.week1;

import java.util.Scanner;
import java.util.Stack;

public class BJ25497_기술연계마스터임스 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();

        Stack<Character> stackLR = new Stack<>();       // LR스킬 담는 스택
        Stack<Character> stackSK = new Stack<>();       // SK스킬 담는 스택
        int result = 0;
        boolean flag = false;
        for (int i=0; i<n; i++) {
            char tmp = s.charAt(i);
            if (flag)  break;                           // 만약에 잘못된 스킬 사용했을 때 break (연계스킬 순서)
            switch (tmp) {                              // 연계스킬 사용
                case 'R':                           // 연계기술
                    if (stackLR.isEmpty()) {        // 비어있음 -> 사전기술을 사용하지 않음
                        flag = true;                // 사용불가 flag = true
                    } else if (stackLR.peek() == 'L') {     // 사전기술을 이전에 사용 -> 가능
                        result++;
                        stackLR.pop();
                    }
                    break;
                case 'K':                   // 연계기술
                    if (stackSK.isEmpty()) {    // 비어있음 -> 사전기술을 사용하지 않음
                        flag = true;            // 사용불가 flag = true
                    } else if (stackSK.peek() == 'S') {     // 사전기술을 이전에 사용 -> 가능
                        result++;
                        stackSK.pop();
                    }
                    break;
                case 'L':                   // 사전기술 : L
                    stackLR.push(tmp);
                    break;
                case 'S':
                    stackSK.push(tmp);       // 사전기술 : R
                    break;
                default:                        // 1-9 사용
                    result++;
            }
        }

        System.out.println(result);

    }
}
