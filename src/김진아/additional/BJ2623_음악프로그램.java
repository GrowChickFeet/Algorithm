package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Singer {

    int number;
    ArrayList<Singer> front;
    ArrayList<Singer> rear;

    public Singer(int number) {
        this.number = number;
        front = new ArrayList<>();
        rear = new ArrayList<>();
    }

}

public class BJ2623_음악프로그램 {

    static int N;
    static Singer[] singers;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        singers = new Singer[N];
        for (int i = 0; i < N; i++) singers[i] = new Singer(i + 1);
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int length = Integer.parseInt(input[0]);
            int front = Integer.parseInt(input[1]) - 1;
            for (int j = 2; j <= length; j++) {
                int rear = Integer.parseInt(input[j]) - 1;
                singers[front].rear.add(singers[rear]);
                singers[rear].front.add(singers[front]);
                front = rear;
            }
        }

        writer.write(sequence());

        writer.close();
        reader.close();
    }

    static String sequence() {
        StringBuilder builder = new StringBuilder();
        boolean[] selected = new boolean[N];

        Queue<Singer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (singers[i].front.isEmpty()) {
                selected[i] = true;
                queue.offer(singers[i]);
            }
        }

        while (!queue.isEmpty()) {
            Singer singer = queue.poll();

            builder.append(singer.number).append("\n");
            for (Singer rear : singer.rear) {
                rear.front.remove(singer);
                if (rear.front.isEmpty()) {
                    selected[rear.number - 1] = true;
                    queue.offer(rear);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (!selected[i]) return "0";
        }
        return builder.toString();
    }

}
