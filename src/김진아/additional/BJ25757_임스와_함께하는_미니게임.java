import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int count = input[1].equals("Y") ? 1 : input[1].equals("F") ? 2 : 3;

        HashSet<String> players = new HashSet<>();
        for (int i = 0; i < N; i++) players.add(reader.readLine());

        writer.write(Integer.toString(players.size() / count));

        writer.close();
        reader.close();
    }

}
