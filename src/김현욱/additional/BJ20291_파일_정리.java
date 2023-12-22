package 김현욱.additional;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BJ20291_파일_정리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Map<String, Integer> maps = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split("\\.");
            String fileName = split[0];
            String extension = split[1];

            maps.put(extension, maps.getOrDefault(extension, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        maps.entrySet().stream()
                .sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey()))
                .forEach(entry -> sb.append(entry.getKey()).append(' ').append(entry.getValue()).append('\n'));
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
