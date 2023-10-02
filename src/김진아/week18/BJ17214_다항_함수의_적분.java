import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String formula = reader.readLine();
        String[] input = formula.split("x");

        int index = 0;
        boolean exist = true;

        if (formula.contains("x")) {
            int num = Integer.parseInt(input[index++]) / 2;
            if (num == -1) builder.append("-xx");
            else if (num == 1) builder.append("xx");
            else builder.append(num).append("xx");
        }

        if (index < input.length) {
            int num = Integer.parseInt(input[index]);
            if (num == 0 && index == 0) exist = false;
            else {
                builder.append(num < 0 || index == 0 ? "" : "+");
                if (num == -1) builder.append("-x");
                else if (num == 1) builder.append("x");
                else builder.append(num).append("x");
            }
        }

        if (exist) builder.append("+");
        builder.append("W");

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
