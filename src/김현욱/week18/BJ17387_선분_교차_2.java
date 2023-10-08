package 김현욱.week18;

import java.io.*;
import java.util.StringTokenizer;

public class BJ17387_선분_교차_2 {
    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int ccw(Point p1, Point p2, Point p3) {
        long ret = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.x * p3.y + p3.x * p2.y + p2.x * p1.y);
        if (ret == 0) return 0;
        else if (ret > 0) return 1;
        else return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);

        st = new StringTokenizer(br.readLine());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());
        int x4 = Integer.parseInt(st.nextToken());
        int y4 = Integer.parseInt(st.nextToken());

        Point p3 = new Point(x3, y3);
        Point p4 = new Point(x4, y4);

        int ccw1 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
        int ccw2 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

        int answer = -1;
        if (ccw1 == 0 && ccw2 == 0) {
            if (Math.min(p1.x, p2.x) <= Math.max(p3.x, p4.x) && Math.min(p3.x, p4.x) <= Math.max(p1.x, p2.x) &&
                    Math.min(p1.y, p2.y) <= Math.max(p3.y, p4.y) && Math.min(p3.y, p4.y) <= Math.max(p1.y, p2.y)) {
                answer = 1;
            } else {
                answer = 0;
            }
        } else if (ccw1 <= 0 && ccw2 <= 0) {
            answer = 1;
        } else {
            answer = 0;
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
