package 박지영.week1;

import java.util.Scanner;

public class SWEA15941_평행사변형 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T= sc.nextInt();
		
		for (int test_case = 1; test_case<=T; test_case++) {
			int n = sc.nextInt();
			sb.append("#").append(test_case).append(" ").append(n*n).append('\n');		// n*n이 가장 넓은 평행 사변형...
		}
		System.out.println(sb.toString());
	}

}
