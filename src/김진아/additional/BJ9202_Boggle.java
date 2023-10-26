package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;

class WordNode {

    boolean isWord;
    String value;
    ArrayList<WordNode> children;

    public WordNode(String value) {
        this.isWord = false;
        this.value = value;
        children = new ArrayList<>();
    }

    public void addChild(WordNode child) {
        children.add(child);
    }

    public WordNode find(String value) {
        for (WordNode child : children) {
            if (child.value.equals(value)) return child;
        }
        return null;
    }

}

public class BJ9202_Boggle {

    final static int SIZE = 4;
    final static int[] SCORE = { 0, 0, 0, 1, 1, 2, 3, 5, 11 };

    final static int DIRECTIONS = 8;
    final static int[] dx = { 1, 1, 1, 0, 0, -1, -1, -1 };
    final static int[] dy = { 1, 0, -1, 1, -1, 1, 0, -1 };

    static WordNode root;
    static String[][] board;
    static HashSet<String> found;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        root = new WordNode(null);

        int w = Integer.parseInt(reader.readLine());
        for (int i = 0; i < w; i++) addWord(reader.readLine());
        reader.readLine();

        int b = Integer.parseInt(reader.readLine());
        for (int i = 0; i < b; i++) {
            board = new String[SIZE][SIZE];
            for (int j = 0; j < SIZE; j++) board[j] = reader.readLine().split("");
            reader.readLine();

            found = new HashSet<>();
            boggle();

            int score = 0;
            int maxLength = 0;
            String longest = "";

            for (String word : found) {
                int length = word.length();
                score += SCORE[length];
                if (length == maxLength && word.compareTo(longest) < 0) longest = word;
                else if (length > maxLength) {
                    maxLength = length;
                    longest = word;
                }
            }

            builder.append(score).append(" ").append(longest).append(" ").append(found.size()).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void boggle() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                WordNode start = root.find(board[i][j]);
                if (start != null) boggle(start, i, j, 1 << (i * SIZE + j));
            }
        }
    }

    static void boggle(WordNode word, int x, int y, int visited) {
        if (word.isWord) found.add(word.value);

        for (int i = 0; i < DIRECTIONS; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE || (visited & (1 << (nx * SIZE + ny))) != 0) continue;

            String now = new StringBuilder().append(word.value).append(board[nx][ny]).toString();
            WordNode nextWord = word.find(now);
            if (nextWord != null) boggle(nextWord, nx, ny, visited | (1 << (nx * SIZE + ny)));
        }
    }

    static void addWord(String word) {
        int length = word.length();
        boolean exist = true;
        WordNode pre = root;

        for (int i = 1; i <= length; i++) {
            String substring = word.substring(0, i);
            WordNode now = null;
            if (exist) {
                now = pre.find(substring);
                if (now == null) exist = false;
            }
            if (!exist) {
                now = new WordNode(substring);
                pre.addChild(now);
            }
            if (i == length) now.isWord = true;
            pre = now;
        }
    }

}
