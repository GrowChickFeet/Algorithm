package 김진아.week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.PriorityQueue;

class Connecting {

    int power;
    double distance;

    public Connecting(int power, double distance) {
        this.power = power;
        this.distance = distance;
    }

}

public class BJ1277_발전소_설치 {

    static int N;
    static double M;
    static int[][] powers;
    static double[][] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int W = Integer.parseInt(input[1]);

        M = Double.parseDouble(reader.readLine());

        powers = new int[N][2];
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            powers[i][0] = Integer.parseInt(input[0]);
            powers[i][1] = Integer.parseInt(input[1]);
        }

        distances = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double distance = Math.sqrt(Math.pow(powers[i][0] - powers[j][0], 2) + Math.pow(powers[i][1] - powers[j][1], 2));
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }

        for (int i = 0; i < W; i++) {
            input = reader.readLine().split(" ");
            int power1 = Integer.parseInt(input[0]) - 1;
            int power2 = Integer.parseInt(input[1]) - 1;
            distances[power1][power2] = 0;
            distances[power2][power1] = 0;
        }

        writer.write(Integer.toString(min()));

        writer.close();
        reader.close();
    }

    static int min() {
        double[] connecting = new double[N];
        for (int i = 0; i < N; i++) connecting[i] = Double.MAX_VALUE;

        PriorityQueue<Connecting> queue = new PriorityQueue<>((connecting1, connecting2) -> Double.compare(connecting1.distance, connecting2.distance));

        connecting[0] = 0;
        queue.offer(new Connecting(0, 0));

        while (!queue.isEmpty()) {
            Connecting now = queue.poll();

            for (int i = 0; i < N; i++) {
                if (distances[now.power][i] > M) continue;
                double distance = now.distance + distances[now.power][i];
                if (distance < connecting[i]) {
                    connecting[i] = distance;
                    queue.offer(new Connecting(i, distance));
                }
            }
        }

        return (int) (connecting[N - 1] * 1000);
    }

}
