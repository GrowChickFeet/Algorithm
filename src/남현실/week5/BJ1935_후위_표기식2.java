package 남현실.week5;

/*
https://www.acmicpc.net/problem/1935
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
[입력]
N: 피연산자의 개수 (1<= N <= 26)
EXP: 후위 표기식
  - A~Z의 영대문자
  - A부터 순서대로 N개의 영대문자만이 사용
  - EXP <= 100

[문제]
-계산 결과 소숫점 둘째 자리까지 출력 (-20억 <= result <= 20억)

[풀이]
- 후위표기식: 피연산자가 먼저 쓰이고 뒤에 연산자가 쓰이는 방식
ex) 4+3 => 43+

- 각 피연산자에 대응하는 값은 순서대로 배열에 저장한다 (피연사-'A' (아스키 코드) 계산을 통해 0인텍스부터 매핑한다)
- 연산자가 나오기 전까지 stack을 이용해 피연산자들을 저장한다
- 연산자가 나오면 stack을 이용해 피연산자 2개를 뽑는다
    - 첫번째 피연산자는 뒤에 두번째 피연산자는 앞에 위치해 계산한다
    - 계산 결과를 다시 stack에 넣는다

** 우리가 익숙한 사칙연산은 '중위표기식'
- 사칙 우선순위를 위해 괄호를 써야한다 ex) 7 * (2 + 3)


 */
public class BJ1935_후위_표기식2 {
    static char[] calcs = {'*', '/', '+', '-'};
    static double[] values;

    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] EXPs = br.readLine().toCharArray();

        values = new double[N];
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        // 후위 표기식 계산
        Stack<Double> stack = new Stack<>();
        double a, b;
        for (char exp : EXPs) {
            if (!isCalc(exp)) { // 연산자가 아니면 stack에 넣기
                stack.push(getValue(exp));
                continue;
            }
            // 연산 후 stack에 넣기
            b = stack.pop();
            a = stack.pop();

            stack.push(calculate(a, b, exp));
        }

        // 소수 둘째 자리까지 표현하여 결과 출력
        System.out.printf("%.2f",(stack.pop()*100.0)/100.0);

    }

    // 연산자인지 판별하는 함수
    static public boolean isCalc(char c) {
        for (char calc : calcs) {
            if (c == calc) {
                return true;
            }
        }
        return false;
    }

    // 문자와 인덱스를 매핑하여 값을 가져오는 함수
    static public double getValue(char c) {
        return values[c - 'A'];
    }

    static public double calculate(double a, double b, char c) {
        switch (c) {
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '+':
                return a + b;
            case '-':
                return a - b;
        }
        return 0;
    }
}
