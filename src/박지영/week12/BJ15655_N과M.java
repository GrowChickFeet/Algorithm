package 박지영.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 재귀를 활용한 수열 구하기
 */
public class BJ15655_N과M {
    static int N, M;
    static int[] arr;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        numbers = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);       // 정렬함
        makeSeq(0, 0);
        System.out.print(sb.toString());
    }

    static void makeSeq(int s, int cnt) {
        // 모두 뿝음
        if (cnt == M) {
            // 출력
            for (int i = 0; i < M; i++) {
                sb.append(arr[numbers[i]]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = s; i < N; i++) {       // 이미 앞에서 뽑은 값에 대해서는 더이상 구하지 않기 위해 시작점을 0이 아닌 s로 시작
            numbers[cnt] = i;
            makeSeq(i+1, cnt+1);
        }
    }

}
