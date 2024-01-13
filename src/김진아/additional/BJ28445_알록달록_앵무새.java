package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.TreeSet;

public class BJ28445_알록달록_앵무새 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        TreeSet<String> parents = new TreeSet<>();
        for (int i = 0; i < 2; i++) {
            String[] input = reader.readLine().split(" ");
            parents.add(input[0]);
            parents.add(input[1]);
        }

        int count = parents.size();
        String[] colors = new String[count];

        int index = 0;
        for (String color : parents) colors[index++] = color;

        TreeSet<String> child = new TreeSet<>();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                child.add(new StringBuilder().append(colors[i]).append(" ").append(colors[j]).toString());
            }
        }

        for (String childColor : child) builder.append(childColor).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
