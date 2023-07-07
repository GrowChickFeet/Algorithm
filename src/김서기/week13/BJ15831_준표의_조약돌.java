package 김서기.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15831_준표의_조약돌 {
    static boolean mp[][];
    static int dy[] = {0,1,0,-1};
    static int dx[] = {1,0,-1,0};
    static int MAX = 200;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int s = 0;
        int e = 0;
        int w = 0;
        int b = 0;
        String str = br.readLine();
        int answer = 0;
        while(e < N){
            if(str.charAt(e++) == 'W'){
                w++;
            }
            else{
                b++;
            }
            if(b > B) {
                e--;
                break;
            }
            if(w >= W){
                answer = Math.max(answer,e-s);
            }
        }
        while(e < N){
            if(str.charAt(e++) == 'W'){
                w++;
            }
            else{
                while(str.charAt(s) != 'B' && s < e){
                    s++;
                    w--;
                }
                s++;
            }
            if(w >= W){
                answer = Math.max(answer,e-s);
            }
        }
        System.out.println(answer);
    }
}
