import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        String skills = reader.readLine();

        int count = 0;
        int L = 0;
        int S = 0;

        for (int i = 0; i < N; i++) {
            char skill = skills.charAt(i);

            if (skill == 'L') L++;
            else if (skill == 'S') S++;
            else if (skill >= '1' && skill <= '9') count++;
            else if (skill == 'R') {
                if (L <= 0) break;
                L--;
                count++;
            } else if (skill == 'K') {
                if (S <= 0) break;
                S--;
                count++;
            }
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
