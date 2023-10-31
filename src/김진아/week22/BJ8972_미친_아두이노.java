package 김진아.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ8972_미친_아두이노 {

    final static int DIRECTIONS = 9;
    final static int[] dx = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
    final static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

    static int R;
    static int C;
    static char[][] board;
    static int[] location;
    static ArrayList<Integer> madLocations;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = reader.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        writer.write(result(reader.readLine()));

        writer.close();
        reader.close();
    }

    static String result(String moving) {
        location = new int[2];
        madLocations = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'R') madLocations.add(i * C + j);
                else if (board[i][j] == 'I') {
                    location[0] = i;
                    location[1] = j;
                }
            }
        }

        int length = moving.length();
        for (int i = 0; i < length; i++) {
            int direction = moving.charAt(i) - '0' - 1;
            move(direction);
            if (board[location[0]][location[1]] != 'I') return new StringBuilder().append("kraj ").append(i + 1).toString();
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) builder.append(board[i][j]);
            builder.append("\n");
        }
        return builder.toString();
    }

    static void move(int direction) {
        int[] moving = { location[0] + dx[direction], location[1] + dy[direction] };
        board[location[0]][location[1]] = '.';
        if (board[moving[0]][moving[1]] == 'R') return;
        board[moving[0]][moving[1]] = 'I';
        location = moving;

        ArrayList<Integer> newMadLocations = new ArrayList<>();
        ArrayList<Integer> removedMadLocations = new ArrayList<>();

        for (int madLocation : madLocations) {
            int preX = madLocation / C;
            int preY = madLocation % C;
            board[preX][preY] = '.';

            int x = -1;
            int y = -1;
            int distance = Integer.MAX_VALUE;

            for (int i = 0; i < DIRECTIONS; i++) {
                int nx = preX + dx[i];
                int ny = preY + dy[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                int nd = Math.abs(location[0] - nx) + Math.abs(location[1] - ny);
                if (nd < distance) {
                    x = nx;
                    y = ny;
                    distance = nd;
                }
            }

            if (x == location[0] && y == location[1]) {
                board[location[0]][location[1]] = 'R';
                return;
            }

            int newMadLocation = x * C + y;
            if (newMadLocations.contains(newMadLocation)) removedMadLocations.add(newMadLocation);
            else newMadLocations.add(newMadLocation);
        }

        newMadLocations.removeAll(removedMadLocations);
        madLocations = newMadLocations;
        for (int madLocation : madLocations) board[madLocation / C][madLocation % C] = 'R';
    }

}
