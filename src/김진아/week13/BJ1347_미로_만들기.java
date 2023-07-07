import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, 0, -1, 0 };
    final static int[] dy = { 0, -1, 0, 1 };

    static boolean[][] movable;
    static int[] range;
    static int direction;
    static int x;
    static int y;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        movable = new boolean[101][101];
        range = new int[] { 50, 50, 50, 50 };
        direction = 0;
        x = 50;
        y = 50;

        int N = Integer.parseInt(reader.readLine());
        String moving = reader.readLine();

        movable[x][y] = true;
        for (int i = 0; i < N; i++) move(moving.charAt(i));

        StringBuilder builder = new StringBuilder();
        for (int x = range[0]; x <= range[2]; x++) {
            for (int y = range[1]; y <= range[3]; y++) {
                if (movable[x][y]) builder.append(".");
                else builder.append("#");
            }
            builder.append("\n");
        }

        writer.write(builder.toString());
        writer.close();
        reader.close();
    }

    static void move(char type) {
        if (type == 'R') direction = (direction + 1) % DIRECTIONS;
        else if (type == 'L') direction = (direction - 1 + DIRECTIONS) % DIRECTIONS;
        else {
            x += dx[direction];
            y += dy[direction];
            movable[x][y] = true;

            range[0] = Math.min(x, range[0]);
            range[1] = Math.min(y, range[1]);
            range[2] = Math.max(x, range[2]);
            range[3] = Math.max(y, range[3]);
        }
    }

}
