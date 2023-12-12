package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ16496_큰_수_만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        String[] nums = reader.readLine().split(" ");

        Arrays.sort(nums, (num1, num2) -> {
            String sum1 = new StringBuilder().append(num1).append(num2).toString();
            String sum2 = new StringBuilder().append(num2).append(num1).toString();
            return sum2.compareTo(sum1);
        });

        if (nums[0].equals("0")) writer.write("0");
        else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < N; i++) builder.append(nums[i]);
            writer.write(builder.toString());
        }

        writer.close();
        reader.close();
    }

}
