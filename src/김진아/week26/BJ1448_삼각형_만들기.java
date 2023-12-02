package 김진아.week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ1448_삼각형_만들기 {

    static int N;
    static int[] straws;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        straws = new int[N];
        for (int i = 0; i < N; i++) straws[i] = Integer.parseInt(reader.readLine());

        writer.write(Integer.toString(triangle()));

        writer.close();
        reader.close();
    }

    static int triangle() {
        Arrays.sort(straws);

        for (int i = N - 1; i >= 2; i--) {
            if (straws[i] < straws[i - 1] + straws[i - 2]) return straws[i] + straws[i - 1] + straws[i - 2];
        }

        return -1;
    }

}
