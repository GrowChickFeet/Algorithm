package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ2822_점수_계산 {

    final static int QUIZ = 8;
    final static int SCORE = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        ArrayList<int[]> quizList = new ArrayList<>();
        for (int i = 1; i <= QUIZ; i++) quizList.add(new int[] { i, Integer.parseInt(reader.readLine()) });

        quizList.sort((quiz1, quiz2) -> quiz2[1] - quiz1[1]);

        ArrayList<Integer> scoreList = new ArrayList<>();
        int totalScore = 0;
        for (int i = 0; i < SCORE; i++) {
            totalScore += quizList.get(i)[1];
            scoreList.add(quizList.get(i)[0]);
        }

        scoreList.sort((score1, score2) -> score1 - score2);

        builder.append(totalScore).append("\n");
        scoreList.forEach((score) -> builder.append(score).append(" "));

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
