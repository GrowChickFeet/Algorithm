package 박지영.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ15903_카드합체놀이 {
    static long N, M;
    static PriorityQueue<Long> queue;       // 우선순위 큐 활용 (long 사용 : 더하면서 값이 커질 경우에 Integer범위를 벗어날 수 있음)
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.offer(Long.parseLong(st.nextToken()));        // 큐에 값 넣기
        }

        for (int i = 0; i < M; i++) {
            long tmp = queue.poll() + queue.poll();     // 가장 작은 두 값을 꺼내서 더하기
            queue.offer(tmp);                           // 다시 두 값을 넣기
            queue.offer(tmp);
        }

        long sum = 0;
        for (int i = 0; i < N; i++) {       // 큐에서 값을 하나씩 꺼내면서 최종 결과 더하기
            sum += queue.poll();
        }
        System.out.print(sum);
    }
}
