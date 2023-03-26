package 성민재.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ1935_후위표기식2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String al = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //알파벳 미리 지정.
    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());//피연산자 개수

        char[] input = br.readLine().toCharArray(); //연산자들

        int[] number = new int[26]; //알파벳에 해당하는 숫자 대응
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(br.readLine()); //해당값 입력
        }

        Stack<Double> stack = new Stack<Double>(); //double을 넣는 스택
        for (int i = 0; i < input.length; i++) {
            if(input[i] == '+' || input[i] == '-' || input[i] == '*' || input[i] == '/' ){ //연산자가 나올 경우
                double second = stack.pop();  //첫번째 피연산자를 스택에서 뽑는다.
                double first = stack.pop(); //두번째 피연산자를 스택에서 뽑는다.
                double temp = opp(first, second, input[i]); //연산을 위한 함수
                stack.push(temp);//연산한 값을 다시 스택에 넣어준다.
            }
            else {
                stack.push((double)number[al.indexOf(String.valueOf(input[i]))]); //알파벳에 대응되는 숫자를 스택에 넣는다.
            }
        }
        System.out.printf("%.2f",stack.pop());
    }
    public static double opp(double a, double b, char o){
        double result = 0;
        if(o == '+'){
            result = a+b; //+인경우
        }
        else if (o == '-') {
            result = a-b; // - 인경우
        }
        else if (o == '*') {
            result = a*b; // *인 경우
        }
        else if (o == '/') {
            result = a/b; // /연산 인 경우
        }
        return result;
    }
}
