package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1475_방_번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String N = reader.readLine();
        int length = N.length();

        int[] count = new int[9];

        for (int i = 0; i < length; i++) {
            int number = N.charAt(i) - '0';
            if (number == 9) number = 6;
            count[number]++;
        }

        count[6] = count[6] / 2 + count[6] % 2;

        int set = 0;
        for (int i = 0; i < 9; i++) set = Math.max(count[i], set);

        writer.write(Integer.toString(set));

        writer.close();
        reader.close();
    }

}
