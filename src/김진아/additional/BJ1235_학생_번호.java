package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

public class BJ1235_학생_번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        String[] students = new String[N];
        for (int i = 0; i < N; i++) students[i] = reader.readLine();

        HashSet<String> numbers = new HashSet<>();
        boolean distinct = false;
        int length = students[0].length();
        int k = 0;

        while (!distinct) {
            distinct = true;
            k++;

            for (int i = 0; i < N; i++) {
                String number = students[i].substring(length - k);
                if (!numbers.contains(number)) numbers.add(number);
                else {
                    distinct = false;
                    break;
                }
            }
        }

        writer.write(Integer.toString(k));

        writer.close();
        reader.close();
    }

}
