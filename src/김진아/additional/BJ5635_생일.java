package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

class Birthday {

    String student;
    String date;

    public Birthday(String student, String year, String month, String day) {
        this.student = student;
        date = new StringBuilder().append(year).append(month.length() == 1 ? "0" : "").append(month).append(day.length() == 1 ? "0" : "").append(day).toString();
    }

}

public class BJ5635_생일 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Birthday> birthdays = new ArrayList<>();

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            birthdays.add(new Birthday(input[0], input[3], input[2], input[1]));
        }

        birthdays.sort((birthday1, birthday2) -> birthday2.date.compareTo(birthday1.date));

        writer.write(new StringBuilder().append(birthdays.get(0).student).append("\n").append(birthdays.get(n - 1).student).toString());

        writer.close();
        reader.close();
    }

}
