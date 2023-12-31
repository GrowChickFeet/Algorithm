package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ2870_수학숙제 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        ArrayList<String> numbers = new ArrayList<>();
        int N = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N; i++) {
            String paper = reader.readLine();
            int paperLength = paper.length();

            StringBuilder numberBuilder = new StringBuilder();
            int numberLength = 0;
            boolean zero = false;

            for (int j = 0; j < paperLength; j++) {
                char character = paper.charAt(j);

                if (character < '0' || character > '9') {
                    if (numberLength > 0) {
                        numbers.add(numberBuilder.toString());
                        numberBuilder.setLength(0);
                        numberLength = 0;
                        zero = false;
                    }
                    continue;
                }

                if (numberLength == 1 && zero) {
                    if (character != '0') {
                        numberBuilder.setLength(0);
                        numberBuilder.append(character);
                        zero = false;
                    }
                    continue;
                }

                if (numberLength == 0 && character == '0') zero = true;
                numberBuilder.append(character);
                numberLength++;
            }

            if (numberLength > 0) numbers.add(numberBuilder.toString());
        }

        numbers.sort((number1, number2) -> {
            int length1 = number1.length();
            int length2 = number2.length();
            if (length1 == length2) return number1.compareTo(number2);
            return length1 - length2;
        });

        for (String number : numbers) builder.append(number).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
