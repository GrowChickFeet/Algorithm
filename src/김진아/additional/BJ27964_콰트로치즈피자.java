package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

public class BJ27964_콰트로치즈피자 {

    static String[] toppings;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        toppings = reader.readLine().split(" ");

        writer.write(quattroCheesePizza() ? "yummy" : "sad");

        writer.close();
        reader.close();
    }

    static boolean quattroCheesePizza() {
        HashSet<String> cheeses = new HashSet<>();
        for (String topping : toppings) {
            if (topping.endsWith("Cheese")) cheeses.add(topping);
        }
        return cheeses.size() >= 4;
    }

}
