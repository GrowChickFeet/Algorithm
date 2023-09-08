import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    static StringBuilder builder;

    static int N;
    static int[] locations;
    static int[] pancakes;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        builder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            String[] input = reader.readLine().split(" ");

            N = Integer.parseInt(input[0]);
            locations = new int[N];
            pancakes = new int[N];

            for (int j = 0; j < N; j++) {
                int pancake = Integer.parseInt(input[j + 1]) - 1;
                locations[pancake] = j;
                pancakes[j] = pancake;
            }

            flipThat();
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void flipThat() {
        StringBuilder flipBuilder = new StringBuilder();

        int count = 0;
        for (int i = N - 1; i >= 0; i--) {
            while (locations[i] != i) {
                int flipLocation = locations[i] == 0 ? i : locations[i];
                flipThat(flipLocation);
                flipBuilder.append(flipLocation + 1).append(" ");
                count++;
            }
        }

        builder.append(count).append(" ").append(flipBuilder).append("\n");
    }

    static void flipThat(int location) {
        int mid = (location + 1) / 2;

        for (int i = 0; i < mid; i++) {
            int pancake1 = pancakes[i];
            int pancake2 = pancakes[location - i];

            pancakes[i] = pancake2;
            pancakes[location - i] = pancake1;

            locations[pancake1] = location - i;
            locations[pancake2] = i;
        }
    }

}
