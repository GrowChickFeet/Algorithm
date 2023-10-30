package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.TreeSet;

public class BJ20291_파일_정리 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        HashMap<String, Integer> files = new HashMap<>();

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String[] fileInfo = reader.readLine().split("\\.");
            files.put(fileInfo[1], files.getOrDefault(fileInfo[1], 0) + 1);
        }

        for (String file : new TreeSet<>(files.keySet())) builder.append(file).append(" ").append(files.get(file)).append("\n");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
