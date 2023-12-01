package 김현욱.week26;

import java.io.*;
import java.util.Arrays;

public class BJ1448_삼각형_만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] lines = new int[n];
        for (int i = 0; i < n; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }

        int result = -1;
        Arrays.sort(lines);
        for (int i = 2; i < n; i++) {
            if (lines[i - 2] + lines[i - 1] > lines[i]) {
                result = lines[i - 2] + lines[i - 1] + lines[i];
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
