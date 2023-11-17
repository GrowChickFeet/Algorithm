package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;

public class BJ7785_회사에_있는_사람 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        HashSet<String> people = new HashSet<>();
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            if (input[1].equals("enter")) people.add(input[0]);
            else people.remove(input[0]);
        }

        ArrayList<String> sortedPeople = new ArrayList<>(people);
        sortedPeople.sort((person1, person2) -> person2.compareTo(person1));
        for (String person : sortedPeople) builder.append(person).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
