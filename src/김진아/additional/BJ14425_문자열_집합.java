package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

public class BJ14425_문자열_집합 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) set.add(reader.readLine());

        int count = 0;
        for (int i = 0; i < M; i++) {
            if (set.contains(reader.readLine())) count++;
        }

        writer.write(Integer.toString(count));

        writer.close();
        reader.close();
    }

}
