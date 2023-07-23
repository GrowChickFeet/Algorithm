import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ13699_점화식 {
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long[] dp;
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(reader.readLine());
		dp = new long[N+1];
		Arrays.fill(dp, -1);
		long answer = func(N);
		System.out.println(answer);
	}
	private static long func(int n) {
		if(n == 0) return 1;
		if(dp[n] != -1) return dp[n];
		
		long ret=0;
		for(int i=0; i<n; ++i) {
			ret += func(i) * func(n-i-1);
		}
		return dp[n] = ret;
	}
}
