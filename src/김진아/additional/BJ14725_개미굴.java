package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

class Food {

    String food;
    ArrayList<Food> next;

    public Food(String food) {
        this.food = food;
        next = new ArrayList<>();
    }

}

public class BJ14725_개미굴 {

    static StringBuilder builder;
    static Food root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        builder = new StringBuilder();

        root = new Food("");

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            int K = Integer.parseInt(input[0]);
            Food now = root;
            for (int j = 1; j <= K; j++) now = insert(now, input[j]);
        }

        structure(root, 0);
        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static Food insert(Food parent, String food) {
        for (Food now : parent.next) {
            if (now.food.equals(food)) return now;
        }
        Food now = new Food(food);
        parent.next.add(now);
        return now;
    }

    static void structure(Food food, int layer) {
        food.next.sort((food1, food2) -> food1.food.compareTo(food2.food));
        for (Food now : food.next) {
            builder.append("--".repeat(layer)).append(now.food).append("\n");
            structure(now, layer + 1);
        }
    }

}
