package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

public class BJ15787_기차가_어둠을_헤치고_은하수를 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[] trains = new int[N];
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int train = Integer.parseInt(input[1]) - 1;
            if (input[0].equals("1")) trains[train] |= (1 << Integer.parseInt(input[2]) - 1);
            else if (input[0].equals("2")) trains[train] &= ~(1 << Integer.parseInt(input[2]) - 1);
            else if (input[0].equals("3")) trains[train] = (trains[train] << 1) & ~(1 << 20);
            else trains[train] >>= 1;
        }

        HashSet<Integer> passed = new HashSet<>();
        for (int i = 0; i < N; i++) passed.add(trains[i]);

        writer.write(Integer.toString(passed.size()));

        writer.close();
        reader.close();
    }

}
