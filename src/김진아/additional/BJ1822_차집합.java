package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.TreeSet;

public class BJ1822_차집합 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int nA = Integer.parseInt(input[0]);
        int nB = Integer.parseInt(input[1]);

        TreeSet<Integer> set = new TreeSet<>();

        input = reader.readLine().split(" ");
        for (int i = 0; i < nA; i++) set.add(Integer.parseInt(input[i]));

        input = reader.readLine().split(" ");
        for (int i = 0; i < nB; i++) set.remove(Integer.parseInt(input[i]));

        builder.append(set.size()).append("\n");
        for (int element : set) builder.append(element).append(" ");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
