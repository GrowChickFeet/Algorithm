package 김현욱.week10;


import java.io.*;
import java.util.StringTokenizer;

public class BJ1890_점프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] maze = new int[n+1][n+1];
        long[][][] dp = new long[n+1][n+1][2];//dp[i][j][0] = i,j에 왼쪽으로 들어오는 경우,dp[i][j][1] = i,j에 위쪽으로 들어오는 경우
        dp[1][1][0] = 0;
        dp[1][1][1] = 1;
        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                maze[i][j] = Integer.parseInt(st.nextToken());

                int dist = maze[i][j];
                long total = dp[i][j][0]+dp[i][j][1];

                if(dist != 0) {
                    //오른쪽으로 가는 경우
                    if (j + dist <= n) {
                        dp[i][j + dist][0] += total;
                    }

                    if(i+dist<=n){
                        dp[i+dist][j][1] +=total;
                    }
                }
            }
        }

        bw.write(Long.toString(dp[n][n][0]+dp[n][n][1]));
        br.close();
        bw.close();
    }
}
