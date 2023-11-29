package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ9466_텀_프로젝트 {

    static int result;
    static int[] want;
    static boolean[] visited;
    static boolean[] teamed;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            int n = Integer.parseInt(reader.readLine());
            String[] input = reader.readLine().split(" ");

            want = new int[n];
            for (int i = 0; i < n; i++) want[i] = Integer.parseInt(input[i]) - 1;
            visited = new boolean[n];
            teamed = new boolean[n];

            result = n;
            for (int i = 0; i < n; i++) team(i);

            builder.append(result).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void team(int now) {
        visited[now] = true;
        int next = want[now];
        if (!visited[next]) team(next);
        else if (!teamed[next]) {
            result--;
            while (next != now) {
                result--;
                next = want[next];
            }
        }
        teamed[now] = true;
    }

}
