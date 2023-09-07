import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        HashMap<Integer, TreeSet<Integer>> sliceX = new HashMap<>();
        HashMap<Integer, TreeSet<Integer>> sliceY = new HashMap<>();

        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            if (!sliceX.containsKey(x)) sliceX.put(x, new TreeSet<>());
            sliceX.get(x).add(y);

            if (!sliceY.containsKey(y)) sliceY.put(y, new TreeSet<>());
            sliceY.get(y).add(x);
        }

        String commands = reader.readLine();
        int x = 0;
        int y = 0;
        for (int i = 0; i < M; i++) {
            char command = commands.charAt(i);
            if (command == 'L') x = sliceY.get(y).lower(x);
            else if (command == 'R') x = sliceY.get(y).higher(x);
            else if (command == 'U') y = sliceX.get(x).higher(y);
            else y = sliceX.get(x).lower(y);
        }

        writer.write(new StringBuilder().append(x).append(" ").append(y).toString());

        writer.close();
        reader.close();
    }

}
