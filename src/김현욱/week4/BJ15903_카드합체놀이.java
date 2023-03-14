package 김현욱.week4;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
* 가장 숫자가 낮은 카드끼리 더해주는게 최적의 값을 도출해낼 수 있다.
*
* */

public class BJ15903_카드합체놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> cards = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            cards.offer(Long.parseLong(st.nextToken()));
        }

        for(int i=0;i<m;i++){
            Long firstCard = cards.poll();
            Long secondCard = cards.poll();

            Long sum = firstCard+secondCard;
            cards.offer(sum);
            cards.offer(sum);
        }
        Long result = 0l;
        while(!cards.isEmpty()){
            result+=cards.poll();
        }


        bw.write(Long.toString(result));
        br.close();
        bw.close();
    }
}
