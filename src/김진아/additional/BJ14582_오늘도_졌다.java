package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ14582_오늘도_졌다 {

    static String[] scores;
    static String[] enemyScores;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        scores = reader.readLine().split(" ");
        enemyScores = reader.readLine().split(" ");

        writer.write(wasWinning() ? "Yes" : "No");

        writer.close();
        reader.close();
    }

    static boolean wasWinning() {
        int score = 0;
        int enemyScore = 0;
        for (int i = 0; i < 9; i++) {
            score += Integer.parseInt(scores[i]);
            if (score > enemyScore) return true;
            enemyScore += Integer.parseInt(enemyScores[i]);
            if (score > enemyScore) return true;
        }
        return false;
    }

}
