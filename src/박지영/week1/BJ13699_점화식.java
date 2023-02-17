package 박지영.week1;

import java.util.Scanner;

public class BJ13699_점화식 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n= sc.nextInt();
		
		long[] arr = new long[36];			// n의 최대가 35이기 때문에 36 크기의 배열 선언
		arr[0] = 1;
		
		for (int i=1; i<=n; i++) {
			long sum = 0;
			for (int j=0; j<i/2; j++) {
				sum += 2*arr[j]*arr[i-j-1];			// t(n) = t(0)*t(n-1)+t(1)*t(n-2)+...+t(n-1)*t(0) 인데 2번 반복으로 더해지므로 *2
			}
			if( i %2 == 1) {		// 홀수일때
				sum += arr[i/2]*arr[i/2];			// 절반을 나눠서 더했으므로 홀수일때 따로 계산
			} 
			arr[i] = sum;
		}
		System.out.println(arr[n]);
	}

}
