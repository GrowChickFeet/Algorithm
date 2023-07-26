package 박지영.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * LinkedList + ListIterator로 해결
 *
 * 인덱스를 통해 접근 -> 시간초과
 * LinkedList에 인덱스를 통한 접근은 하나하나 확인하면서 처리해서 시간초과가 난다.
 *
 * ListIterator : 양방향으로 탐색 가능
 */
public class BJ1406_에디터 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();   // 초기 문자
        LinkedList<Character> list = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }

        int N = Integer.parseInt(br.readLine());    // 입력될 개수
        ListIterator<Character> iter = list.listIterator();
        while(iter.hasNext()) {     // 맨 끝으로 이동
            iter.next();
        }

        for (int i = 0; i < N; i++) {
            s = br.readLine();
            char m = s.charAt(0);

            switch (m) {
                case 'L':       // 커서 왼쪽으로 이동
                    if (iter.hasPrevious()) {
                        iter.previous();
                    }
                    break;
                case 'D':       // 커서 오른쪽으로 이동
                    if (iter.hasNext()) {
                        iter.next();
                    }
                    break;
                case 'B':       // 커서의 왼쪽 지우기
                    if (iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                    break;
                case 'P':       // 커서의 오른쪽에 새로운 문자 입력
                    char x = s.charAt(2);
                    iter.add(x);
                    break;
            }

        }

        // 출력
        for (Character c : list) {
            sb.append(c);
        }
        System.out.print(sb.toString());
    }
}
