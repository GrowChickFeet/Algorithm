package 김현욱.additional;

import java.io.*;

public class BJ2011_암호_해독 {
    static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] numbers = br.readLine().toCharArray();
        int len = numbers.length;
        int answer = 0;
        if (numbers[0] != '0') {//0번째 자리에 0이 올 수 없음
            /**
             * dp[i][0] = i번째 수가 한자리수로 해석되는 경우
             * dp[i][1] = i번째 수가 두자리수의 1의 자리로 해석되는 경우
             * dp[i][2] = i번째 수가 두자리수의 10의 자리로 해석되는 경우
             */
            int[][] dp = new int[len][3];
            dp[0][0] = 1;//0번째 자리가 한자리수로 무조건 해석 가능
            dp[0][1] = 0;//0번째 자리는 두자리수의 1의자리로 해석 불가.
            dp[0][2] = (numbers[0] <= '2' ? 1 : 0);//0번째 자리가 1 또는 2인 경우는 두자리수의 10의 자리로 해석 가능
            for (int i = 1; i < numbers.length; i++) {
                /**
                 * i번째 자리가 한자리수로 해석되는 경우
                 * 이 경우는 i-1번째까지 해석이 완성이 된 경우에 해당 경우의 수를 더해주면됨
                 * 따라서 (이전 자리의 수가 한자리수로 해석된 경우 + 두자리수의 1의자리로 해석된 경우 ) 이 된다.
                 * 이 경우는, 0이 되면 안됨
                 */
                if(numbers[i] != '0') {
                    dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
                }
                /**
                 * i번째 자리가 두자리수의 1의자리로 해석되는 경우
                 * 이 경우는 i-1번째 자리가 두자리수의 10의자리로 해석되는 경우의 연장선
                 * 따라서 (이전 자리의 수가 두자리수의 10의자리로 해석된 경우의 경우의수) 가 된다.
                 * 하지만, 바로 이전의 자리수가 1또는 2일때만 가능하고, 2일때는 해당 자리가 무조건 0~6 사이여야 한다.
                 */
                if (numbers[i - 1] == '1' || (numbers[i - 1] == '2' && (numbers[i] >= '0' && numbers[i] <= '6'))) {
                    dp[i][1] = (dp[i - 1][2]) % MOD;
                }

                /**
                 * i번째 자리가 두자리수의 10의 자리로 해석되는 경우
                 * 이 경우는 i-1번째까지 해석이 완성이 된 경우에 해당 경우의수를 더해주면됨
                 * 따라서 (이전 자리의 수가 한자리수로 해석된 경우 + 두자리수의 1의자리로 해석된 경우 ) 이 된다.
                 * 이 경우는, 해당 자리가 1 or 2인 경우만 가능하다.
                 */
                if (numbers[i] == '1' || numbers[i] == '2') {
                    dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
                }
            }
            /**
             * 정답은 len까지 완성된 경우의수를 더해주면됨
             */
            answer = (dp[len - 1][0] + dp[len - 1][1]) % MOD;
        }

        bw.write(Integer.toString(answer % MOD));
        br.close();
        bw.close();
    }
}
