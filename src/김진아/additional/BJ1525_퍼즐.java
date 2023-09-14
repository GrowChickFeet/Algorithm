import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Puzzle {

    final static int SIZE = 3;

    int x;
    int y;
    int count;
    int status;
    int[][] puzzle;

    static Puzzle getInstance(int[][] puzzle, int count) {
        Puzzle instance = new Puzzle();
        instance.puzzle = puzzle;
        instance.count = count;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                instance.status = instance.status * 10 + puzzle[i][j];
                if (puzzle[i][j] == 0) {
                    instance.x = i;
                    instance.y = j;
                }
            }
        }

        return instance;
    }

}

public class Main {

    final static int SIZE = 3;
    final static int CORRECT = 123456780;

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] puzzle = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < SIZE; j++) puzzle[i][j] = Integer.parseInt(input[j]);
        }

        writer.write(Integer.toString(count(puzzle)));

        writer.close();
        reader.close();
    }

    static int count(int[][] puzzle) {
        HashSet<Integer> visited = new HashSet<>();
        Queue<Puzzle> queue = new LinkedList<>();

        Puzzle start = Puzzle.getInstance(puzzle, 0);
        if (start.status == CORRECT) return 0;

        visited.add(start.status);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Puzzle now = queue.poll();

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) continue;

                int[][] nextPuzzle = copy(now.puzzle);
                nextPuzzle[nx][ny] += nextPuzzle[now.x][now.y];
                nextPuzzle[now.x][now.y] = nextPuzzle[nx][ny] - nextPuzzle[now.x][now.y];
                nextPuzzle[nx][ny] = nextPuzzle[nx][ny] - nextPuzzle[now.x][now.y];

                Puzzle next = Puzzle.getInstance(nextPuzzle, now.count + 1);
                if (next.status == CORRECT) return next.count;

                if (visited.contains(next.status)) continue;
                visited.add(next.status);
                queue.offer(next);
            }
        }

        return -1;
    }

    static int[][] copy(int[][] origin) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) copy[i][j] = origin[i][j];
        }
        return copy;
    }

}
