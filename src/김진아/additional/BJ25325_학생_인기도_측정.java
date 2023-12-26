package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;

public class BJ25325_학생_인기도_측정 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int n = Integer.parseInt(reader.readLine());

        HashMap<String, Integer> star = new HashMap<>();
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) star.put(input[i], 0);

        for (int i = 0; i < n; i++) {
            input = reader.readLine().split(" ");
            for (String student : input) star.put(student, star.get(student) + 1);
        }

        star.entrySet().stream().sorted((student1, student2) -> {
            int star1 = student1.getValue();
            int star2 = student2.getValue();
            if (star1 == star2) return student1.getKey().compareTo(student2.getKey());
            return star2 - star1;
        }).forEach(student -> builder.append(student.getKey()).append(" ").append(student.getValue()).append("\n"));

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
