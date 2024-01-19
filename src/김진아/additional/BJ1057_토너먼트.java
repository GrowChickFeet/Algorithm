package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1057_토너먼트 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int jimin = Integer.parseInt(input[1]) - 1;
        int hansu = Integer.parseInt(input[2]) - 1;

        writer.write(Integer.toString(round(N, jimin, hansu)));

        writer.close();
        reader.close();
    }
    
    static int round(int N, int jimin, int hansu) {
        int round = 0;
        while (jimin != hansu) {
            jimin = jimin / 2 + jimin % 2;
            hansu = hansu / 2 + hansu % 2;
            round++;
        }
        return round;
    }

}
