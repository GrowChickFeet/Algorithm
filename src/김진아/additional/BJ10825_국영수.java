package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

class Result {

    String name;
    int koreanScore;
    int englishScore;
    int mathScore;

    public Result(String name, int koreanScore, int englishScore, int mathScore) {
        this.name = name;
        this.koreanScore = koreanScore;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
    }

}

public class BJ10825_국영수 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        Result[] results = new Result[N];
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            results[i] = new Result(input[0], Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
        }

        Arrays.stream(results).sorted((result1, result2) -> {
            if (result1.koreanScore != result2.koreanScore) return result2.koreanScore - result1.koreanScore;
            if (result1.englishScore != result2.englishScore) return result1.englishScore - result2.englishScore;
            if (result1.mathScore != result2.mathScore) return result2.mathScore - result1.mathScore;
            return result1.name.compareTo(result2.name);
        }).forEach((result -> builder.append(result.name).append("\n")));

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
