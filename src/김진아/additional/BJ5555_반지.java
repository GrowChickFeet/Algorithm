package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ5555_반지 {

    final static int RING_LENGTH = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String target = reader.readLine();
        int length = target.length();

        int N = Integer.parseInt(reader.readLine());
        int count = 0;

        for (int i = 0; i < N; i++) {
            String ring = reader.readLine();
            for (int j = 0; j < RING_LENGTH; j++) {
                boolean found = true;
                for (int k = 0; k < length; k++) {
                    if (ring.charAt((j + k) % RING_LENGTH) != target.charAt(k)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    count++;
                    break;
                }
            }
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
