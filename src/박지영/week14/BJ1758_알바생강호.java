package 박지영.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1758_알바생강호 {
    static int N;
    static long maxTip;
    static int[] tips;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tips = new int[N];
        for (int i = 0; i < N; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(tips);
        for (int i = N-1; i >=0 ; i--) {
            maxTip += Math.max(tips[i] - (N - i-1), 0);
        }

        System.out.print(maxTip);
    }

}
