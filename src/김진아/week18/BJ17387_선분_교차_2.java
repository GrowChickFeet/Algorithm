package 김진아.week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ17387_선분_교차_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        long[][] line1 = new long[2][2];
        line1[0][0] = Long.parseLong(input[0]);
        line1[0][1] = Long.parseLong(input[1]);
        line1[1][0] = Long.parseLong(input[2]);
        line1[1][1] = Long.parseLong(input[3]);

        input = reader.readLine().split(" ");
        long[][] line2 = new long[2][2];
        line2[0][0] = Long.parseLong(input[0]);
        line2[0][1] = Long.parseLong(input[1]);
        line2[1][0] = Long.parseLong(input[2]);
        line2[1][1] = Long.parseLong(input[3]);

        writer.write(isCrossed(line1[0][0], line1[0][1], line1[1][0], line1[1][1], line2[0][0], line2[0][1], line2[1][0], line2[1][1]) ? "1" : "0");

        writer.close();
        reader.close();
    }

    static boolean isCrossed(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        int ccw123 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw124 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw341 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw342 = ccw(x3, y3, x4, y4, x2, y2);

        if (ccw123 == 0 && ccw124 == 0 && ccw341 == 0 && ccw342 == 0) return Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2)
                                                                            && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2);
        return ccw123 * ccw124 <= 0 && ccw341 * ccw342 <= 0;
    }

    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long ccw = x1 * y2 + x2 * y3 + x3 * y1 - y1 * x2 - y2 * x3 - y3 * x1;
        return ccw == 0 ? 0 : ccw > 0 ? 1 : -1;
    }

}
