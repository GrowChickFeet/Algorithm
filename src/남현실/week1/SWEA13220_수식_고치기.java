package 남현실.week1;

/*
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AXzjz2V6-Q8DFASs
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA13220_수식_고치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        char[] arr;
        int calcx0; // x가 0인 경우
        int calcx1; // x가 1인 경우
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            arr = br.readLine().toCharArray();
            calcx0 = calc(arr, 0);
            calcx1 = calc(arr, 1);

            result.append("#").append(tc).append(" ").append((calcx0 == calcx1) ? 0 : 1).append("\n");
        }
        System.out.println(result.toString());

        br.close();
    }

    public static int calcBit(int a, int b, char op) {
        if (op == '|') {
            return a | b;
        } else if (op == '^') {
            return a ^ b;
        } else if (op == '&') {
            return a & b;
        }
        return -1;
    }

    public static int calc(char[] arr, int xVal) { // [xVal] 0/1
        Stack<Character> opStack = new Stack<>();
        Stack<Integer> valStack = new Stack<>();
        Stack<Character> brackStack = new Stack<>();


        for (char c : arr) {
            if (c == '(') {
                brackStack.push('(');
            } else if (c == ')') {
                brackStack.pop();
                valStack.push(calcBit(valStack.pop(), valStack.pop(), opStack.pop()));
            } else if (c == '|' || c == '^' || c == '&') {
                opStack.push(c);
            } else if (c == 'x') {
                valStack.push(xVal);
            } else if (c == 'X') {
                valStack.push((xVal + 1) % 2);
            } else {
                valStack.push(Character.getNumericValue(c));
            }
        }
        return valStack.pop();
    }
}
