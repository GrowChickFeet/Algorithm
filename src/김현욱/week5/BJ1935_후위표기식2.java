package 김현욱.week5;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BJ1935_후위표기식2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        char[] ch = br.readLine().toCharArray();
        double[] value = new double[n];

        for(int i=0;i<n;i++){
            value[i] = Double.parseDouble(br.readLine());
        }

        Stack<Double> st = new Stack<>();

        for(char c : ch){
            if(isOperation(c)){
                double v2 = st.pop();
                double v1 = st.pop();
                double result = 0;
                switch(c){
                    case '+':
                        result =v1+v2;
                        break;
                    case '-':
                        result=v1-v2;
                        break;
                    case '/':
                        result = v1/v2;
                        break;
                    case '*':
                        result =v1*v2;
                        break;
                    default:
                        break;
                }
                st.push(result);
            }
            else{
                st.push(value[c-'A']);
            }
        }
        bw.write(String.format("%.2f",st.pop()));
        br.close();
        bw.close();
    }

    private static boolean isOperation(char c) {
        return c=='+'||c=='-'||c=='*'||c=='/';
    }
}
