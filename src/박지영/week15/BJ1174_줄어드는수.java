package 박지영.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**수
 * 0~9까지의 수를 고른 다음에 정렬하도록 했습니다(백트래킹으로 중복이 없도록)
 * 같은 수의 경우에는 줄어드는 수가 아니기 때문에 가능
 */
public class BJ1174_줄어드는수 {
    static int[] numbers;       // 0~9까지 수 선택
    static ArrayList<Long> list = new ArrayList<>();        // 줄어드는 수를 모아두는 배열

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        numbers = new int[10];

        getNumbers(0, 0);
        list.sort(new Comparator<Long>() {  // list 정렬
            @Override
            public int compare(Long o1, Long o2) {
                return Long.compare(o1, o2);
            }
        });

        if (list.size() <= N+1) System.out.println(-1);
        else System.out.print(list.get(N+1));
    }

    // 줄어드는 수 -> 숫자로 변경
    static long getLong() {
        String s = "";
        for (int i = 0; i < 10; i++) {
            if (numbers[9-i] == 1) {
                s += (9-i);
            }

        }
        return s.equals("") ? 0 : Long.parseLong(s);
    }

    static void getNumbers(int n, int cnt) {    // 숫자 선택
        if (n == 10) {
            list.add(getLong());        // 줄어드는 수로 저장
            return;
        }
        numbers[n] = 0;
        getNumbers(n+1, cnt);
        numbers[n] = 1;
        getNumbers(n+1, cnt+1);
    }
}
