package 김현욱.week12;

import java.io.*;
import java.util.StringTokenizer;

public class BJ17245_서버실 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] maze = new int[n][n];
        long total=0;
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                maze[i][j] = Integer.parseInt(st.nextToken());
                total+=maze[i][j];
            }
        }

        int left = 0;
        int right = 10_000_000;


        while(left<right){
            int middle = (left+right)>>1;

            long sum = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    sum += Math.min(middle,maze[i][j]);
                }
            }

            if(sum >= total/2.0){
                right = middle;
            }
            else{
                left = middle+1;
            }
        }

        bw.write(Integer.toString(right));
        br.close();
        bw.close();
    }
}
