package 김현욱.week15;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ1406_에디터 {
    static StringTokenizer st = null;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        char[] arr = br.readLine().toCharArray();
        for(int i=0;i<arr.length;i++){
            left.push(arr[i]);
        }

        int n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);

            if (c == 'L') {
                if(!left.isEmpty()){
                    right.push(left.pop());
                }
            }
            else if(c == 'D'){
                if(!right.isEmpty()){
                    left.push(right.pop());
                }
            }
            else if(c =='B'){
                if(!left.isEmpty()){
                    left.pop();
                }
            }
            else if(c=='P'){
                char v = st.nextToken().charAt(0);
                left.push(v);
            }
        }

        while(!left.isEmpty()){
            right.push(left.pop());
        }
        StringBuilder sb = new StringBuilder();
        while(!right.isEmpty()){
            sb.append(right.pop());
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
