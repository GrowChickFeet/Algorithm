import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;
            Map<Integer, Integer> count = new HashMap<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    count.put(num, count.getOrDefault(num, 0) + 1);
                }
            }

            count.entrySet()
                    .stream()
                    .collect(Collectors.groupingBy(Map.Entry::getValue, TreeMap::new, Collectors.toList()))
                    .descendingMap()
                    .values()
                    .stream()
                    .skip(1)
                    .findFirst()
                    .orElse(Collections.emptyList())
                    .stream().map(Map.Entry::getKey)
                    .sorted((o1, o2) -> Integer.compare(o1, o2))
                    .forEach(v -> sb.append(v).append(' '));
            sb.append('\n');
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
