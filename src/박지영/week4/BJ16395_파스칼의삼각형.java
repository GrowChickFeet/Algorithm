package 박지영.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16395_파스칼의삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n =Integer.parseInt(st.nextToken());
        int k =Integer.parseInt(st.nextToken());

        long result = 1;        // 곱하기를 했을 때 범위가 벗어날 수 있기 때문에 long으로 선언

        int count = Math.min(n-k, k-1);     // 조합 계산할 때 더 작은 계산을 위해서 차가 더 작은 값을 계산
        for (int i = 1; i < count+1; i++) {
            result *= (n-i);
        }
        for (int i = 0; i < count; i++) {
            result /= (count-i);
        }
        System.out.println(result);
    }

}
