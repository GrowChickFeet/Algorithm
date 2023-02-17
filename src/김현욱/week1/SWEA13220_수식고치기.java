package 김현욱.week1;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class SWEA13220_수식고치기 {
    static StringBuilder sb = new StringBuilder();//문제의 출력을 위한 string Builder;
    public static void main(String[] args) throws IOException {//main 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//시스템 입력에서 입력을 받기위해 buffered reader 설정
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//시스템 출력에 출력을 하기위해 buffered writer 설정

        int T = Integer.valueOf(br.readLine());//테스트 케이스 개수 입력

        for(int test_case = 1;test_case <=T;test_case ++) {//테스트케이스을 돌리기 위한 for문
            char[] line1 = br.readLine().toCharArray();
            char[] line2 = Arrays.copyOf(line1,line1.length);

            int len = line1.length;
            for(int i=0;i<len;i++){//x=0인 경우와 x=1인 경우의 line을 모두 만들어버림
                if(line1[i] == 'x'){
                    line1[i] = '1';
                    line2[i] = '0';
                }
                else if(line1[i] == 'X'){
                    line1[i] = '0';
                    line2[i] = '1';
                }
            }

            int r1 = calculate(line1);//x=0을 대입했을때의 결과값을 얻어옴
            int r2 = calculate(line2);//x=1을 대입했을때의 결과값을 얻어옴
            int result = (r1==r2 ? 0 : 1);//만약 결과값이 같다면 바꿀필요 없고, 결과 값이 다르다면, 다른 결과에 맞는 최종 연산자 1개만 바꿔주면 됨
            sb.append('#').append(test_case).append(' ').append(result).append('\n');//정답을 stringbuilder에 입력
        }

        bw.write(sb.toString());//buffered writer에 결과 저장
        bw.close();//buffered writer 자원 반환
        br.close();//buffered reader 자원 반환
    }
    private static int calc(int v1,int v2,char op){//v1 op v2 일때의 결과값을 얻어오는 함수
        switch(op){
            case '&':
                return v1&v2;
            case '|':
                return v1|v2;
            case '^':
                return v1^v2;
            default:
                return -1;
        }
    }
    private static int calculate(char[] line) {
        int len = line.length;
        Stack<Character> bracket = new Stack<>();
        Stack<Character> op = new Stack<>();
        Stack<Integer> number = new Stack<>();

        for(int i=0;i<len;i++){//문자열을 돌며 수식의 순서에 맞게 계산
            char c = line[i];
            if(c == '('){
                bracket.push(c);
            }
            else if(c == ')'){
                int num1 = number.pop();
                int num2 = number.pop();
                char operator = op.pop();
                int result = calc(num1,num2,operator);
                number.push(result);
            }
            else if(c == '1' || c == '0'){
                number.push(c-'0');
            }
            else{
                op.push(c);
            }
        }
        return number.pop();//마지막 결과값을 반환
    }
}
