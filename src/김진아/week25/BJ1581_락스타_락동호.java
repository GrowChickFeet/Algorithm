package 김진아.week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1581_락스타_락동호 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int FF = Integer.parseInt(input[0]);
        int FS = Integer.parseInt(input[1]);
        int SF = Integer.parseInt(input[2]);
        int SS = Integer.parseInt(input[3]);

        writer.write(Integer.toString(max(FF, FS, SF, SS)));

        writer.close();
        reader.close();
    }

    static int max(int FF, int FS, int SF, int SS) {
        if (FF == 0 && FS == 0) return SS + Math.min(SF, 1);
        if (FS == 0) return FF;
        if (FS > SF) return FF + Math.min(FS, 0) + SF * 2 + Math.min(FS, 1) + SS;
        return FF + FS * 2 + Math.min(SF, 0) + SS;
    }

}
