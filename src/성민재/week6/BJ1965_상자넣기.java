package 성민재.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1965_상자넣기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] input = new int[n]; //입력값
        int[] dp = new int[n]; //dp 배열 생성

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1; //dp의 각 인덱스들은 모두 다른상자를 넣지 않더라도 한개의 상자를 가지고 있다.
        }
        int ans = 0; //정답을 담을 변수
        for (int i = 1; i < n; i++) { //0은 첫번째이기 때문에 최대로 담을 수 있는 상자가 1이기때문에 빼고 진행한다.
            for (int j = 0; j < i; j++) { //i번째 상자보다 작은 사이즈의 상자를 찾아야 한다.
                if(input[i] > input[j]){ //현재 내 상자보다 작을 때 들어간다.
                    dp[i] = Math.max(dp[i], dp[j]+1); //dp에는 각 인덱스가 몇개의 상자를 담을 수 있는지 정보가 담겨있다.
                    //그래서 나보다 작은 상자 중에서 가장 많은 상자를 담고 있는 상자를 선택해준다.
                }
            }
            ans = Math.max(dp[i],ans); //가장 많이 담겨있는 상자를 고른다.
        }
        System.out.println(ans);
    }
}
