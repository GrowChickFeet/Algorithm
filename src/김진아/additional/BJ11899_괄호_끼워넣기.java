package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ11899_괄호_끼워넣기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();

        int length = line.length();
        int openCount = 0;
        int count = 0;

        for (int i = 0; i < length; i++) {
            if (line.charAt(i) == '(') openCount++;
            else {
                if (openCount > 0) openCount--;
                else count++;
            }
        }

        writer.write(Integer.toString(count + openCount));

        writer.close();
        reader.close();
    }

}
