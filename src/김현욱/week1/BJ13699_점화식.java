package 김현욱.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ13699_점화식 {

	static long dp[];//이전에 계산했던 값을 저장하기 위한 배열
	public static long calc(int number) {
		if(number == 0) {//t(0) = 1
			return dp[number] = 1;
		}
		if(dp[number]!=0) {// t(number)가 이전에 계산했던 수열이면 기억해뒀던 값 리턴
			return dp[number];
		}
		long result = 0;//결과값
		for(int i=0;i<=number-1;i++) {//문제에서 주어진 수열을 통해 값을 구함
			result += calc(i)*calc(number-i-1);
		}
		return dp[number] = result;//해당 값을 기억하며 return
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int n = Integer.valueOf(br.readLine());
		dp = new long[n+1];
		bw.write(Long.toString(calc(n)));//t(n)을 구하기 위해 재귀호출 시작

		bw.close();
		br.close();
	}

}
