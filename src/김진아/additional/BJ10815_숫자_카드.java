package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

public class BJ10815_숫자_카드 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        HashSet<Integer> numbers = new HashSet<>();

        int N = Integer.parseInt(reader.readLine());
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) numbers.add(Integer.parseInt(input[i]));

        int M = Integer.parseInt(reader.readLine());
        input = reader.readLine().split(" ");
        for (int i = 0; i < M; i++) builder.append(numbers.contains(Integer.parseInt(input[i])) ? "1" : "0").append(" ");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
