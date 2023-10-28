package 김현욱.additional;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2162_선분_그룹 {
    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            } else return Integer.compare(this.x, o.x);
        }
    }

    static class Node {
        Point p1, p2;

        public Node(int sx, int sy, int ex, int ey) {
            Point t1 = new Point(sx, sy);
            Point t2 = new Point(ex, ey);

            p1 = t1.compareTo(t2) <= 0 ? t1 : t2;
            p2 = t1.compareTo(t2) <= 0 ? t2 : t1;
        }
    }

    public static int ccw(Point p1, Point p2, Point p3) {
        long ret = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.x * p3.y + p3.x * p2.y + p2.x * p1.y);
        if (ret == 0) return 0;
        else if (ret > 0) return 1;
        else return -1;
    }

    public static boolean isCross(Node n1, Node n2) {
        Point p1 = n1.p1;
        Point p2 = n1.p2;

        Point p3 = n2.p1;
        Point p4 = n2.p2;

        int ccw1 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
        int ccw2 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

        if (ccw1 == 0 && ccw2 == 0) {
            if (Math.min(p1.x, p2.x) <= Math.max(p3.x, p4.x) && Math.min(p3.x, p4.x) <= Math.max(p1.x, p2.x)
                    && Math.min(p1.y, p2.y) <= Math.max(p3.y, p4.y) && Math.min(p3.y, p4.y) <= Math.max(p1.y, p2.y)) {
                return true;
            } else {
                return false;
            }
        } else if (ccw1 <= 0 && ccw2 <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent, parent[x]);
    }

    public static void union(int[] parent, int[] count, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        if (a == b) return;

        int h = count[a] < count[b] ? a : b;
        int l = count[a] < count[b] ? b : a;

        count[h] += count[l];
        count[l] = -1;
        parent[l] = parent[h];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Node[] lines = new Node[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            lines[i] = new Node(sx, sy, ex, ey);
        }

        int[] parent = new int[n];
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            count[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isCross(lines[i], lines[j])) {
                    union(parent, count, i, j);
                }
            }
        }
        int result = 0;
        int resultCount = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) result++;
            resultCount = Math.max(resultCount, count[i]);
        }


        StringBuilder sb = new StringBuilder();
        sb.append(result).append('\n').append(resultCount);
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
