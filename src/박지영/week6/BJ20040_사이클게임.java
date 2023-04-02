package 박지영.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// union-find를 이용한 사이클 확인
public class BJ20040_사이클게임 {
    static int N, M;
    static int[] parents;
    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;       // 부모 노드가 같다면 cycle 발생
        } else {                // 아니라면 union 수행
            if (a > b) {
                parents[b] = a;
            } else {
                parents[a]=b;
            }
            return true;
        }
    }

    static int find(int a) {        // 부모 노드 찾기
        if (parents[a] == a)  return a;
        return parents[a] = find(parents[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N];
        for (int i = 0; i < N; i++) {       // 초기값 -> 자기자신을 부모 노드로
            parents[i] = i;
        }

        int result = 0;
        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!union(a, b)) {     // union이 불가능 -> 사이클 발생
                result = i;       // 해당 순서를 결과로 저장
                break;
            }
        }

        System.out.print(result);
    }
}
