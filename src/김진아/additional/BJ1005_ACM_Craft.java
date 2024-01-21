package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Building {

    int number;
    int buildingTime;
    int time;
    HashSet<Building> pre;
    HashSet<Building> next;

    public Building(int number, int buildingTime) {
        this.number = number;
        this.buildingTime = buildingTime;
        time = 0;
        pre = new HashSet<>();
        next = new HashSet<>();
    }

}

public class BJ1005_ACM_Craft {

    static Building[] buildings;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            String[] input = reader.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int K = Integer.parseInt(input[1]);

            buildings = new Building[N];
            input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) buildings[j] = new Building(j + 1, Integer.parseInt(input[j]));

            for (int j = 0; j < K; j++) {
                input = reader.readLine().split(" ");
                int pre = Integer.parseInt(input[0]) - 1;
                int next = Integer.parseInt(input[1]) - 1;

                buildings[pre].next.add(buildings[next]);
                buildings[next].pre.add(buildings[pre]);
            }

            builder.append(time(Integer.parseInt(reader.readLine()))).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static int time(int building) {
        Queue<Building> queue = new LinkedList<>();

        for (Building now : buildings) {
            if (!now.pre.isEmpty()) continue;
            now.time = now.buildingTime;
            if (now.number == building) return now.time;
            queue.offer(now);
        }

        while (!queue.isEmpty()) {
            Building now = queue.poll();

            for (Building next : now.next) {
                next.pre.remove(now);
                next.time = Math.max(now.time + next.buildingTime, next.time);
                if (!next.pre.isEmpty()) continue;
                if (next.number == building) return next.time;
                queue.offer(next);
            }
        }

        return -1;
    }

}
