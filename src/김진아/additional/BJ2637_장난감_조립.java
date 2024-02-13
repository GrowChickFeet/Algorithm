package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class ToyPiece {

    int number;
    int[] count;
    HashSet<ToyPiece> sub;
    HashSet<ToyPiece> sur;

    public ToyPiece(int number, int N) {
        this.number = number;
        count = new int[N];
        sub = new HashSet<>();
        sur = new HashSet<>();
    }

    public void addSub(ToyPiece piece, int count) {
        sub.add(piece);
        this.count[piece.number] += count;
    }

    public void addSur(ToyPiece piece) {
        sur.add(piece);
    }

}

public class BJ2637_장난감_조립 {

    static int N;
    static ToyPiece[] pieces;

    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        N = Integer.parseInt(reader.readLine());
        pieces = new ToyPiece[N + 1];
        for (int i = 1; i <= N; i++) pieces[i] = new ToyPiece(i, N);

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) {
            String[] input = reader.readLine().split(" ");
            int sur = Integer.parseInt(input[0]);
            int sub = Integer.parseInt(input[1]);
            int count = Integer.parseInt(input[2]);
            pieces[sur].addSub(pieces[sub], count);
            pieces[sub].addSur(pieces[sur]);
        }

        count = new int[N + 1];
        assemble();

        for (int i = 1; i <= N; i++) {
            if (pieces[i].sub.isEmpty()) builder.append(i).append(" ").append(count[i]).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void assemble() {
        Queue<ToyPiece> queue = new LinkedList<>();

        count[N] += 1;
        queue.offer(pieces[N]);

        while (!queue.isEmpty()) {
            ToyPiece now = queue.poll();

            for (ToyPiece piece : now.sub) {
                count[piece.number] += count[now.number] * now.count[piece.number];
                piece.sur.remove(now);
                if (piece.sur.isEmpty()) queue.offer(piece);
            }
        }
    }

}
