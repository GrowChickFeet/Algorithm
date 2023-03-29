package 박지영.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


//스택을 활용하여 문제 해결
public class BJ1935_후위표현식2 {
    static Stack<Double> stack = new Stack<>(); // 계산에 활용되는 스택
    static double[] abc;        // A, B, C,,, 에 해당하는 숫자(Double)을 담는 배열
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();       // 계산할 공식을 arr배열에 저장
        abc = new double[N];
        for (int i = 0; i < N; i++) {
            abc[i] = Double.parseDouble(br.readLine());     // 해당 숫자를 담는 반복문
        }
        for (char c : arr) {        // 각문자에 대하여 계산
            solution(c);
        }
        System.out.print(String.format("%.2f", stack.pop()));       // stack에 담겨있는 마지막을 꺼내서 소숫점 2자리에 맞추기
    }

    // 계산
    static void solution(char k) {      // 각 case에 대하여 switch문 -> 계산, 사칙연산이 아니면 그냥 스택에 넣음
        double a, b;
        switch (k) {
            case '+':
                a = stack.pop() ;
                b = stack.pop() ;
                stack.push(b+a);
                break;
            case '-':
                a = stack.pop() ;
                b = stack.pop() ;
                stack.push(b-a);
                break;
            case '*':
                a = stack.pop() ;
                b = stack.pop() ;
                stack.push(b*a);
                break;
            case '/':
                a = stack.pop() ;
                b = stack.pop() ;
                stack.push(b/a);
                break;
            default:
                Double tmp = abc[(k-65)];
                stack.push(tmp);
        }
    }
}
