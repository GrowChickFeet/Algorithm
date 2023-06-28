package 김현욱.additional;

import java.io.*;
import java.util.StringTokenizer;

public class BJ1669_멍멍이쓰다듬기 {
    public static long segma(int target){
        return (target*(target+1))/2;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int monkey = Integer.parseInt(st.nextToken());
        int puppy = Integer.parseInt(st.nextToken());

        long gap = puppy - monkey;
        long result = 0;

        if(gap == 0){
            bw.write(Integer.toString(0));
        }
        else {
            while(result*result < gap) result++;
            result = result*result == gap ? result : result - 1;

            long remain = gap - result;
            long answer = result;

            while(remain>0){
                remain -= Math.min(remain,result);
                answer++;
            }
            bw.write(Long.toString(answer));
        }
        br.close();
        bw.close();
    }
}
