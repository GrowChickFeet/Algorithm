package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2419_사수아탕 {
    static int n, m, x;
    static int[][][] dp = new int[330][330][2];
    static List<Integer> position = new ArrayList<>();

    public static int getDp(int left, int right, int remain, int pos) {
        if (remain == 0) return 0;
        if (dp[left][right][pos] != -1) {
            return dp[left][right][pos];
        }

        dp[left][right][pos] = Integer.MAX_VALUE;

        int now = (pos == 0 ? position.get(left) : position.get(right));

        if (left > 0) {
            dp[left][right][pos] = Math.min(dp[left][right][pos], remain * (now - position.get(left-1)) + getDp(left-1,right,remain-1,0));
        }
        if (right < n - 1) {
            dp[left][right][pos] = Math.min(dp[left][right][pos],remain * (position.get(right+1) - now) + getDp(left,right+1,remain-1,1));
        }
        return dp[left][right][pos];
    }

    public static int lowerBound(int target){
        int left = 0;
        int right = position.size();

        while(left<right){
            int middle = (left+right)>>1;

            if(position.get(middle) >= target){
                right = middle;
            }
            else{
                left = middle+1;
            }
        }
        return right;
    }

    public static void resetDPTable() {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boolean zeroContains = false;

        for (int i = 0; i < n; i++) {
            int pos = Integer.parseInt(br.readLine());
            position.add(pos);
            if (pos == 0) zeroContains = true;
        }
        if (!zeroContains) {
            n++;
            position.add(0);
        }

        position.sort(Integer::compare);
        x = lowerBound(0);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            resetDPTable();
            answer = Math.max(answer,i*m - getDp(x,x,i,0));
        }

        bw.write(Integer.toString(answer + (zeroContains ? m : 0)));
        br.close();
        bw.close();
    }
}
