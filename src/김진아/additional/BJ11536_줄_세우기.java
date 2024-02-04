package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ11536_줄_세우기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        String[] names = new String[N];
        for (int i = 0; i < N; i++) names[i] = reader.readLine();

        int sorted = names[0].compareTo(names[1]);
        for (int i = 1; i < N - 1; i++) {
            if (names[i].compareTo(names[i + 1]) * sorted < 0) {
                sorted = 0;
                break;
            }
        }

        writer.write(sorted == 0 ? "NEITHER" : sorted > 0 ? "DECREASING" : "INCREASING");

        writer.close();
        reader.close();
    }

}
