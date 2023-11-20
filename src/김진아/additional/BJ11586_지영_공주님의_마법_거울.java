package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ11586_지영_공주님의_마법_거울 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        String[] mirror = new String[N];
        for (int i = 0; i < N; i++) mirror[i] = reader.readLine();

        int K = Integer.parseInt(reader.readLine());
        if (K == 1) {
            for (int i = 0; i < N; i++) builder.append(mirror[i]).append("\n");
        } else if (K == 2) {
            for (int i = 0; i < N; i++) builder.append(new StringBuilder(mirror[i]).reverse()).append("\n");
        } else {
            for (int i = N - 1; i >= 0; i--) builder.append(mirror[i]).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
