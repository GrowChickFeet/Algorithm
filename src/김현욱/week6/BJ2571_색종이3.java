package 김현욱.week6;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2571_색종이3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] maze = new int[200][200];
        int[][] sum = new int[200][200];
        int MAX_VALUE = 10001;//너비의 최댓값+1
        int n = Integer.parseInt(br.readLine());

        for(int i=0;i<200;i++){
            Arrays.fill(maze[i],-MAX_VALUE);
        }

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())+1;
            int y = Integer.parseInt(st.nextToken())+1;
            for(int j=0;j<10;j++){
                for(int k=0;k<10;k++){
                    maze[x+j][y+k] = 1;
                }
            }
        }
        int result = 0;
        for(int i=1;i<=100;i++){
            for(int j=1;j<=100;j++){
                sum[i][j] = maze[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
            }
        }

        for(int i=1;i<=100;i++){
            for(int j=1;j<=100;j++){
                for(int i1=i+1;i1<=100;i1++){
                    for(int j1=j+1;j1<=100;j1++){
                        int value = (sum[i1][j1] - sum[i1][j-1] - sum[i-1][j1] + sum[i-1][j-1]);
                        result = Math.max(result,value);
                    }
                }
            }
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
