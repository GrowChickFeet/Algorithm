package 성민재.week4;

import java.util.Scanner;

public class BJ16395_파스칼의삼각형 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] pa = new int[n+1][n+1];

        pa[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            pa[i][1] = 1;
            for (int j = 2; j <= i; j++) {
                pa[i][j] = pa[i-1][j-1] + pa[i-1][j];
            }
        }
        System.out.println(pa[n][k]);
    }
}