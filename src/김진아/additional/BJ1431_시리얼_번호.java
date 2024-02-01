package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ1431_시리얼_번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        ArrayList<String> serials = new ArrayList<>();
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) serials.add(reader.readLine());

        serials.sort((serial1, serial2) -> {
            int length1 = serial1.length();
            int length2 = serial2.length();
            if (length1 != length2) return length1 - length2;

            int sum1 = 0;
            for (int i = 0; i < length1; i++) {
                char character = serial1.charAt(i);
                if (character >= '0' && character <= '9') sum1 += character - '0';
            }
            int sum2 = 0;
            for (int i = 0; i < length2; i++) {
                char character = serial2.charAt(i);
                if (character >= '0' && character <= '9') sum2 += character - '0';
            }
            if (sum1 != sum2) return sum1 - sum2;

            return serial1.compareTo(serial2);
        });

        for (String serial : serials) builder.append(serial).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
