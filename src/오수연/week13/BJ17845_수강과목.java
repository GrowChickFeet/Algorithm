import java.io.*;
import java.util.*;

public class Main {
	
	static int limitTime;
	static int subCnt;
	static Sub[] subs;
	static int[][] dp;
	static class Sub{
		int w;
		int time;
		Sub(int w, int time){
			this.w = w;
			this.time = time;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] input = reader.readLine().split(" ");
		limitTime = Integer.parseInt(input[0]);
		subCnt = Integer.parseInt(input[1]);
		
		subs = new Sub[subCnt+1];
		dp = new int[subCnt+1][limitTime+1];
//		subs[0] = new Sub(0, 0);
		for(int i=1; i<=subCnt; ++i) {
			input = reader.readLine().split(" ");
			subs[i] = new Sub(Integer.parseInt(input[0]),Integer.parseInt(input[1]));
		}
		
		for(int i=1; i<=subCnt; ++i) {
			getMaxW(i);
		}
		
		int answer = dp[subCnt][limitTime];
//		for(int i=1; i<=subCnt; ++i) {
//			answer = Math.max(dp[i][limitTime], answer);
//		}
		
		System.out.println(answer);

	}
	private static void getMaxW(int num) {
		for(int t=0; t<=limitTime; ++t) {
			dp[num][t] = dp[num-1][t];
		}
		for(int t=subs[num].time; t<=limitTime; ++t) {
			dp[num][t] = Math.max( dp[num-1][t], dp[num-1][t-subs[num].time]+subs[num].w);
//			System.out.print(dp[num][t] + " ");
		}
	}

}