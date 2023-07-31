package 김현욱.week14;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10164_격자상의_경로 {

    static int n, m, k;

    public static long getCase(int start, int end) {//start -> end 까지 오른쪽과 아래로 내려갈 수 있는 경우의 수
        int sx = (start / m) + 1;
        int sy = (start % m) + 1;

        int ex = end / m + 1;
        int ey = end % m + 1;

        int[][] dp = new int[n + 1][m + 1];
        dp[sx][sy] = 1;
        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                dp[i][j] += dp[i][j-1]+dp[i-1][j];//현재 위치까지의 경우의 수 = 위에서 내려오는 경우의 수 + 왼쪽에서 오른쪽으로 넘어오는 경우의 수
            }
        }

        return dp[ex][ey];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        //k가 0이면 시작부터 끝까지, 아니라면 0~k-1까지의 경우의 수 * k-1 ~ n*m-1까지의 경우의 수를 출력
        bw.write(Long.toString(k==0 ? getCase(0,n*m-1) : getCase(0,k-1) * getCase(k-1,n*m-1)));
        br.close();
        bw.close();
    }
}
