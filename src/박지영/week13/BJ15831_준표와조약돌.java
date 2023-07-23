package 박지영.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15831_준표와조약돌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        char[] arr = br.readLine().toCharArray();

        int whiteCnt = 0;
        int blackCnt = 0;
        int length = 0;
        int end = 0;
        for (int s = 0; s < N; s++) {
            if (arr[s] == 'W') whiteCnt++;
            else blackCnt++;

            while(blackCnt > B) {
                if (arr[++end] == 'W') whiteCnt--;
                else blackCnt--;
            }

            if (blackCnt <= B && whiteCnt >= W ) length = Math.max(length, s-end);
        }

        System.out.print(length);
    }
}