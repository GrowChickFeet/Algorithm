package 김진아.week17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

class Fireball {

    int mass;
    int speed;
    int direction;

    public Fireball(int mass, int speed, int direction) {
        this.mass = mass;
        this.speed = speed;
        this.direction = direction;
    }

    public String toString() {
        return mass+"::"+speed+"::"+direction;
    }

}

public class BJ20056_마법사_상어와_파이어볼 {

    final static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    final static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

    static int N;
    static HashMap<Integer, ArrayList<Fireball>> fireballs;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        fireballs = new HashMap<>();
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int location = (Integer.parseInt(input[0]) - 1) * N + Integer.parseInt(input[1]) - 1;
            if (!fireballs.containsKey(location)) fireballs.put(location, new ArrayList<>());
            fireballs.get(location).add(new Fireball(Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4])));
        }

        for (int i = 0; i < K; i++) move();

        int sum = 0;
        for (int i = 0; i < N * N; i++) {
            if (!fireballs.containsKey(i)) continue;
            for (Fireball fireball : fireballs.get(i)) sum += fireball.mass;
        }

        writer.write(Integer.toString(sum));

        writer.close();
        reader.close();
    }

    static void move() {
        HashMap<Integer, ArrayList<Fireball>> movedFireballs = new HashMap<>();

        for (int i = 0; i < N * N; i++) {
            if (!fireballs.containsKey(i)) continue;

            int x = i / N;
            int y = i % N;

            for (Fireball fireball : fireballs.get(i)) {
                int nx = x + dx[fireball.direction] * fireball.speed;
                while (nx < 0) nx += N;
                nx %= N;

                int ny = y + dy[fireball.direction] * fireball.speed;
                while (ny < 0) ny += N;
                ny %= N;

                int location = nx * N + ny;
                if (!movedFireballs.containsKey(location)) movedFireballs.put(location, new ArrayList<>());
                movedFireballs.get(location).add(fireball);
            }
        }

        for (int i = 0; i < N * N; i++) {
            if (!movedFireballs.containsKey(i)) continue;

            ArrayList<Fireball> fireballList = movedFireballs.get(i);
            int count = fireballList.size();
            if (count <= 1) continue;

            int mass = 0;
            int speed = 0;
            int direction = 0;

            for (Fireball fireball : fireballList) {
                mass += fireball.mass;
                speed += fireball.speed;
                direction += fireball.direction % 2;
            }

            mass /= 5;
            speed /= count;
            direction = direction == 0 || direction == count ? 0 : 1;

            if (mass == 0) {
                movedFireballs.remove(i);
                continue;
            }

            ArrayList<Fireball> newFireballList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                newFireballList.add(new Fireball(mass, speed, direction));
                direction += 2;
            }

            movedFireballs.replace(i, newFireballList);
        }

        fireballs = movedFireballs;
    }

}
