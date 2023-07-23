package 박지영.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1669_멍멍이쓰다듬기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int monkey = Integer.parseInt(st.nextToken());
        int dog = Integer.parseInt(st.nextToken());

        int len = dog - monkey;
        int n = (int) Math.sqrt(len);

        if (len == 0) System.out.print(0);
        else if (len == n*n) System.out.print(n*2-1);
        else if (len <= n*(n+1)) System.out.print(n*2);
        else System.out.print(n*2+1);
    }
}
