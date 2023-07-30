package 김현욱.week14;

import java.io.*;
import java.util.StringTokenizer;

public class BJ14719_빗물 {
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        boolean[][] maze = new boolean[h+1][w+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<w;i++){
            int height = Integer.parseInt(st.nextToken());
            for(int j=0;j<height;j++){
                maze[j][i] = true;
            }
        }
        int result = 0;

        for(int i=0;i<h;i++){
            boolean start = false;
            int startIndex = -1;
            for(int j=0;j<w;j++){
                if(!start && maze[i][j]){
                    start = true;
                    startIndex = j;
                }
                else if(start && maze[i][j]){
                    result += (j-startIndex-1);
                    startIndex = j;
                }
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
