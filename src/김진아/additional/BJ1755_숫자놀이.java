package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ1755_숫자놀이 {

    final static String[] numbers = {
            "zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten"
    };

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);

        ArrayList<String[]> numberStrings = new ArrayList<>();
        for (int i = M; i <= N; i++) {
            String[] number = new String[2];
            number[0] = Integer.toString(i);

            StringBuilder numberStringBuilder = new StringBuilder();
            int length = number[0].length();
            for (int j = 0; j < length; j++) numberStringBuilder.append(numbers[number[0].charAt(j) - '0']);
            number[1] = numberStringBuilder.toString();

            numberStrings.add(number);
        }

        numberStrings.sort((numberString1, numberString2) -> numberString1[1].compareTo(numberString2[1]));

        int count = N - M + 1;
        for (int i = 0; i < count; i++) {
            builder.append(numberStrings.get(i)[0]).append(" ");
            if (i % 10 == 9) builder.append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
