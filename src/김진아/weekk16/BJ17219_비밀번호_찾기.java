import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        HashMap<String, String> passwords = new HashMap<>();
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            passwords.put(input[0], input[1]);
        }

        for (int i = 0; i < M; i++) builder.append(passwords.get(reader.readLine())).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
