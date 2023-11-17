package 김진아.week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;

public class BJ10546_배부른_마라토너 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, Integer> participants = new HashMap<>();

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String name = reader.readLine();
            participants.put(name, participants.getOrDefault(name, 0) + 1);
        }
        for (int i = 1; i < N; i++) {
            String name = reader.readLine();
            participants.put(name, participants.get(name) - 1);
            if (participants.get(name) == 0) participants.remove(name);
        }

        writer.write(participants.keySet().iterator().next());

        writer.close();
        reader.close();
    }

}
