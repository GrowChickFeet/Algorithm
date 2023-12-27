package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.StringTokenizer;

public class BJ1343_폴리오미노 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(fill(reader.readLine()));

        writer.close();
        reader.close();
    }

    static String fill(String board) {
        StringBuilder builder = new StringBuilder();

        StringTokenizer tokens = new StringTokenizer(board, ".");
        int length = board.length();

        for (int i = 0; i < length; i++) {
            if (board.charAt(i) == '.') {
                builder.append(".");
                continue;
            }

            String token = tokens.nextToken();
            int tokenLength = token.length();

            if (tokenLength % 2 != 0) return "-1";

            for (int j = 0; j < tokenLength / 4; j++) builder.append("AAAA");
            if (tokenLength % 4 != 0) builder.append("BB");

            i += tokenLength - 1;
        }

        return builder.toString();
    }

}
