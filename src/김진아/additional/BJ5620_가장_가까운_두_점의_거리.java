package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.TreeSet;

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

public class BJ5620_가장_가까운_두_점의_거리 {

    final static int MIN = -10000;
    final static int MAX = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            points[i] = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        Arrays.sort(points, (point1, point2) -> {
            if (point1.x == point2.x) return point1.y - point2.y;
            return point1.x - point2.x;
        });

        TreeSet<Point> pointSet = new TreeSet<>((point1, point2) -> {
            if (point1.y == point2.y) return point1.x - point2.x;
            return point1.y - point2.y;
        });

        int min = distance(points[0].x, points[0].y, points[1].x, points[1].y);
        int startIndex = 0;

        pointSet.add(points[0]);
        pointSet.add(points[1]);

        for (int i = 2; i < n; i++) {
            while (startIndex < i) {
                int distance = (points[startIndex].x - points[i].x) * (points[startIndex].x - points[i].x);
                if (distance <= min) break;
                pointSet.remove(points[startIndex++]);
            }

            int distance = (int) Math.sqrt(min);
            Point start = new Point(MIN, points[i].y - distance);
            Point end = new Point(MAX, points[i].y + distance);
            for (Point point : pointSet.subSet(start, end)) min = Math.min(distance(point.x, point.y, points[i].x, points[i].y), min);

            pointSet.add(points[i]);
        }

        writer.write(Integer.toString(min));

        writer.close();
        reader.close();
    }

    static int distance(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}
