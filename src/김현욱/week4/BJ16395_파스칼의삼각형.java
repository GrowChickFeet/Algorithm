package 김현욱.week4;

import java.io.*;
import java.util.StringTokenizer;

public class BJ16395_파스칼의삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] maze = new int[31][31];

        maze[1][1]=1;
        for(int i=1;i<=30;i++){
            maze[i][1] = 1;
            for(int j=2;j<i;j++){
                maze[i][j] = maze[i-1][j-1]+maze[i-1][j];
            }
            maze[i][i] = 1;
        }

        bw.write(Integer.toString(maze[n][k]));

        br.close();
        bw.close();
    }
}
